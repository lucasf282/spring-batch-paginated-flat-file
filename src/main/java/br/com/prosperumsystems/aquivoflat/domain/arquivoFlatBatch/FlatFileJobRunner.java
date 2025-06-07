package br.com.prosperumsystems.aquivoflat.domain.arquivoFlatBatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.event.ApplicationReadyEvent;

@Component
public class FlatFileJobRunner {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job flatFileJob;

    @EventListener(ApplicationReadyEvent.class)
    public void runJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(flatFileJob, params);
    }
}