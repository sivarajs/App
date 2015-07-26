package meru.scheduler;

import java.io.Serializable;

public interface Job extends Serializable {
  public void execute(JobExecutionContext executionContext);
}