package i2par.placement.schedule.job;

import meru.app.service.schedule.AppScheduleJob;

public class PlacementScheduleJob extends AppScheduleJob {

    @Override
    public void execute() {

/*        EntityQuery<Placement> entityQuery = appEngine.createQuery(Placement.class);

        List<Placement> placementList = appEngine.get(entityQuery);

        for (Placement placement : placementList) {
            if (appEngine.getEntityLifeCycle(Placement.class,
                                             PlacementLifeCycle.class)
                         .setState(placement)) {
                appEngine.save(placement);
            }
        }
*/
    }
}
