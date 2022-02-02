package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class consumeCargo extends CommandBase{
    public consumeCargo() {

    }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
      Robot.intake.setSpeed(0.5);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
      return false;
  }

  @Override
  public boolean runsWhenDisabled() {
    return false;
  }
}