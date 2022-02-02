package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class toggleIntake extends CommandBase{
    
    public toggleIntake() {
        addRequirements(Robot.intake);
    }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
      Robot.intake.toggleSolenoids();
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