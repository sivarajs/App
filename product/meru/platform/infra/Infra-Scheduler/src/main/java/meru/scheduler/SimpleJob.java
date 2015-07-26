package meru.scheduler;


public class SimpleJob
  implements Job {
  
  private static final long serialVersionUID = 1L;

  public void execute(JobExecutionContext executionContext) {
    System.out.println("####### Executed");
  }
}