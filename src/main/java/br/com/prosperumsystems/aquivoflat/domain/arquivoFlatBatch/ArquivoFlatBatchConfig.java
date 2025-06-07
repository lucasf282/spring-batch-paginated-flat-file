package br.com.prosperumsystems.aquivoflat.domain.arquivoFlatBatch;

import br.com.prosperumsystems.aquivoflat.model.FlatFilePaginationInfo;
import br.com.prosperumsystems.aquivoflat.model.Posicao;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class ArquivoFlatBatchConfig {

    @Bean
    public Job flatFileJob(JobRepository jobRepository, @Qualifier("flatFileStep") Step flatFileStep) {
        return new JobBuilder("flatFileJob", jobRepository)
                .start(flatFileStep)
                .build();
    }

    @Bean
    public Step flatFileStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                             @Qualifier("PosicaoVerificaHeaderProcessor") ItemProcessor<Posicao, Posicao> posicaoVerificaHeaderProcessor) {
        return new StepBuilder("flatFileStep", jobRepository)
                .<Posicao, Posicao>chunk(100, transactionManager)
                .reader(posicaoReader())
                .processor(posicaoVerificaHeaderProcessor)
                .writer(flatFileWriter())
                .build();
    }

    @Bean
    public FlatFileItemReader<Posicao> posicaoReader() {
        return new FlatFileItemReaderBuilder<Posicao>()
                .name("posicaoReader")
                .resource(new ClassPathResource("posicoes.csv"))
                .delimited()
                .delimiter(",")
                .names("dataMovimento", "documento", "nome", "nuProduto", "nomeFundoInvestimento", "cnpjFundo", "operacao", "dataEfetivacao", "dataConversao", "qtdeCotas", "valorInicial", "saldoBruto", "saldoLiquido", "cotaInicial", "cotaAtual", "iof", "ir", "formaLiquidacao", "agencia", "operacaoProduto", "contaDv")
                .targetType(Posicao.class)
                .linesToSkip(1) // skip header
                .build();
    }

    @Bean
    public FlatFileItemWriter<Posicao> flatFileWriter() {
        return new FlatFileItemWriterBuilder<Posicao>()
                .name("flatFileWriter")
                .resource(new FileSystemResource("output.txt"))
                .lineAggregator(posicaoLineAggregator())
                .build();
    }

    private String formatDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private String formatTime(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    private LineAggregator<Posicao> posicaoLineAggregator() {
        return posicao -> {
            StringBuilder sb = new StringBuilder();
            if (posicao.getPaginationInfo().temCabecalho()) {
                sb.append(pageHeader(posicao));
            }
            sb.append(PosicaoFormat(posicao));
            if (posicao.getPaginationInfo().temRodape()) {
                sb.append(pageFooter());
            }
            return sb.toString();
        };
    }

    private String pageHeader(Posicao posicao) {
        String dataAtual = formatDate(LocalDateTime.now());
        String horaAtual = formatTime(LocalDateTime.now());
        String paginaAtual = posicao.getPaginationInfo().getPaginaAtual().toString();
        String codigoAgencia = String.format("%04d", Integer.valueOf(posicao.getAgencia()));
        StringBuilder sb = new StringBuilder();
        sb.append("1====================================================================================================================================");
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
        sb.append(" ").append(dataAtual).append(" PZ ARQ:  20A                                          EMPRESA FICTICIA" + getWithSpaces(41-paginaAtual.length()) + "PAG - " + paginaAtual + ".ZZ9");
        sb.append(System.lineSeparator());
        sb.append(" ").append(horaAtual).append("                                      SIGLA - SISTEMA DE GESTAO DE LATENTCIA E ALGORITIMOS                                  ");
        sb.append(System.lineSeparator());
        sb.append("                                               RELATORIO DE POSICAO DIARIA EM FUNDOS DE INVESTIMENTO    DATA DO MOVIMENTO: ").append(posicao.getDataMovimento());
        sb.append(System.lineSeparator());
        sb.append(" " + codigoAgencia + " - NOME AGENCIA                                                                                                                 ");
        sb.append(System.lineSeparator());
        sb.append(" ------------------------------------------------------------------------------------------------------------------------------------");
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    private String getWithSpaces(int length) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length) {
            sb.append(" ");
        }
        return sb.toString();
    }

    private String pageFooter() {
        return System.lineSeparator() + " ====================================================================================================================================";
    }

    private String emptyLine() {
        return getWithSpaces(133) + System.lineSeparator();
    }

    private String PosicaoFormat(Posicao posicao) {
        return String.join(",",
                posicao.getPaginationInfo().getPaginaAtual().toString(),
                posicao.getPaginationInfo().getNumeroItemPagina().toString(),
                posicao.getPaginationInfo().getQtdTotalItens().toString(),
                posicao.getAgencia(),
                String.valueOf(posicao.getPaginationInfo().temCabecalho()),
                String.valueOf(posicao.getPaginationInfo().temRodape()),
                posicao.getDataMovimento(),
                posicao.getDocumento(),
                posicao.getNome(),
                posicao.getNuProduto(),
                posicao.getNomeFundoInvestimento(),
                posicao.getCnpjFundo(),
                posicao.getOperacao(),
                posicao.getDataEfetivacao(),
                posicao.getDataConversao(),
                posicao.getQtdeCotas(),
                posicao.getValorInicial().toString(),
                posicao.getSaldoBruto().toString(),
                posicao.getSaldoLiquido().toString(),
                posicao.getCotaInicial().toString(),
                posicao.getCotaAtual().toString(),
                posicao.getIof().toString(),
                posicao.getIr().toString(),
                posicao.getFormaLiquidacao(),
                posicao.getAgencia(),
                posicao.getOperacaoProduto(),
                posicao.getContaDv());
    }
}

@Component("PosicaoVerificaHeaderProcessor")
@StepScope
class PosicaoVerificaHeaderProcessor implements ItemProcessor<Posicao, Posicao> {
    private StepExecution stepExecution;
    private Long QUANTIDADE_ITENS_POR_PAGINA = 10L;

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    @Override
    public Posicao process(Posicao posicao) throws Exception {
        addCountItensPage();
        if (deveAvancarPagina(posicao.getAgencia())){
            nextPage();
        }
        Long qtdTotalItens = stepExecution.getReadCount(); // TODO: adicionar o tatal de itens lidos no Step context ao executar o reader do step
        FlatFilePaginationInfo paginationInfo = new FlatFilePaginationInfo(
            getCountPages(),
            getCountItensPage(),
            QUANTIDADE_ITENS_POR_PAGINA,
            qtdTotalItens);
        stepExecution.getExecutionContext().put("ultimaAgencia", posicao.getAgencia());
        posicao.setPaginationInfo(paginationInfo);
        return posicao;
    }

    private void nextPage() {
        addCountPages();
        resetCountItensPage();
    }

    private boolean deveAvancarPagina(String agenciaAtual) {
        String ultimaAgencia = stepExecution.getExecutionContext().get("ultimaAgencia", String.class);
        Long countItensPage = getCountItensPage();
        boolean isFirstPosicaoDaAgencia = !agenciaAtual.equals(ultimaAgencia);
        boolean passouLimitePagina = countItensPage > QUANTIDADE_ITENS_POR_PAGINA;
        return isFirstPosicaoDaAgencia || passouLimitePagina;
    }

    private Long addCountItensPage() {
        Long countItensPage = 0L;
        if (stepExecution.getExecutionContext().containsKey("countItensPage")) {
            countItensPage = stepExecution.getExecutionContext().getLong("countItensPage");
        }
        countItensPage++;
        stepExecution.getExecutionContext().putLong("countItensPage", countItensPage);
        addCountItensTotal();
        return countItensPage;
    }

    private Long addCountItensTotal() {
        Long countItensTotal = 0L;
        if (stepExecution.getExecutionContext().containsKey("countItensTotal")) {
            countItensTotal = stepExecution.getExecutionContext().getLong("countItensTotal");
        }
        countItensTotal++;
        stepExecution.getExecutionContext().putLong("countItensTotal", countItensTotal);
        return countItensTotal;
    }

    private Long addCountPages() {
        Long countPages = 0L;
        if (stepExecution.getExecutionContext().containsKey("countPages")) {
            countPages = stepExecution.getExecutionContext().getLong("countPages");
        }
        countPages++;
        stepExecution.getExecutionContext().putLong("countPages", countPages);
        return countPages;
    }

    private Long getCountItensPage() {
        return stepExecution.getExecutionContext().getLong("countItensPage", 1L);
    }

    private Long getCountItensTotal() {
        return stepExecution.getExecutionContext().getLong("countItensTotal", 1L);
    }

    private Long getCountPages() {
        return stepExecution.getExecutionContext().getLong("countPages", 1L);
    }

    private void resetCountItensPage() {
        stepExecution.getExecutionContext().putLong("countItensPage", 1L);
    }
}
