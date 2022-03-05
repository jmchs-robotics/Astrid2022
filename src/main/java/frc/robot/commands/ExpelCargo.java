package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RollerIntake;

public class ExpelCargo extends CommandBase{

    RollerIntake m_subsystem;

    /**
     * @param subsystem
     */

    public ExpelCargo(RollerIntake subsystem) {
      
        m_subsystem = subsystem;
        addRequirements(m_subsystem);

    }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    m_subsystem.setSpeed(-0.2);
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
