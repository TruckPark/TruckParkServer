package de.htwg.moco.truckparkserver.jobs;

import de.htwg.moco.truckparkserver.service.CleanUserUpdateService;
import de.htwg.moco.truckparkserver.service.PredictionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CronJob {


    private final CleanUserUpdateService cleanUserUpdateService;
    private final PredictionsService predictionsService;

    @Autowired
    public CronJob(CleanUserUpdateService cleanUserUpdateService, PredictionsService predictionsService) {
        this.cleanUserUpdateService = cleanUserUpdateService;
        this.predictionsService = predictionsService;
    }


    @Scheduled(cron = "0 0 * * * *") //every hour (00:00, 01:00...)
    public void calcPrediction() {
        predictionsService.calc();
    }

    @Scheduled(cron = "0 0/30 * * * *") //every 30mins (00:00, 00:30, 01:00...)
    public void cleanUserUpdate() {
        cleanUserUpdateService.clean();
    }

}
