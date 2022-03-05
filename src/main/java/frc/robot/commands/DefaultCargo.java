package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.RollerIntake;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DefaultCargo extends CommandBase {
  
  private final RollerIntake m_subsystem;
  private final XboxController m_stick;
  private double intakeControl;
  private double extakeControl;

  /**
   *
   * @param subsystem The subsystem used by this command.
   * @param stick The XBoxController used by this command.
   */
  public DefaultCargo(RollerIntake subsystem, XboxController stick) {
      m_subsystem = subsystem;
      m_stick = stick;
      addRequirements(subsystem);
  }
        
    

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    extakeControl = m_stick.getRightTriggerAxis() * 0.2;
    intakeControl = m_stick.getLeftTriggerAxis() * 0.2;

    if(intakeControl > extakeControl) {
        m_subsystem.setSpeed(intakeControl);
    }
    else {
        m_subsystem.setSpeed(-extakeControl);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      m_subsystem.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      return false;
  }
} 
