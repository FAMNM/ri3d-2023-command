package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class WaitCommand extends CommandBase {

    private final double timeToWait;
    private double start;

    public WaitCommand(double time) {
        this.timeToWait = time;
    }

    @Override
    public void initialize() {
      start = Timer.getFPGATimestamp();
    }
  
    @Override
    public void end(boolean interrupted) {
    }
  
    @Override
    public boolean isFinished() {
      return Math.abs(Timer.getFPGATimestamp() - start) > timeToWait;
    }
  
}
