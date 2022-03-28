package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IntakeSubsystem;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ScoreCargo extends CommandBase {
  
  private final IntakeSubsystem m_subsystem;
  private final XboxController m_stick;

  /**
   *
   * @param subsystem The subsystem used by this command.
   * @param stick The XBoxController used by this command.
   */
  public ScoreCargo(IntakeSubsystem subsystem, XboxController stick) {
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
    double moveValue = m_stick.getLeftTriggerAxis() - m_stick.getRightTriggerAxis();
    SmartDashboard.putNumber("Move Value: ", moveValue);


    m_subsystem.setSpeed(m_subsystem.deadband(moveValue));
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
