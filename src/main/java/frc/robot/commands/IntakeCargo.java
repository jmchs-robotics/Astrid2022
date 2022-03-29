package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCargo extends CommandBase{

    IntakeSubsystem m_subsystem;

    /**
     * @param subsystem
     */

    public IntakeCargo(IntakeSubsystem subsystem) {
      
        m_subsystem = subsystem;
        addRequirements(m_subsystem);

    }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    m_subsystem.setSpeed(0.2);
  }

  @Override
  public void end(boolean interrupted) {
    m_subsystem.stopMotors();
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
