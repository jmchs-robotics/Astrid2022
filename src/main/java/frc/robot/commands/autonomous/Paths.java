package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

/**
 *
 */
public class Paths { // extends CommandBase {
 
    //private SwerveDriveSubsystem m_swerve;
    Drivetrain m_drive;
    HookSubsystem m_hook;
    LArmSubsystem m_LArm;
    RollerIntake m_intake;

    /**
     * @param drive
     * @param hook
     * @param clip
     * @param intake
     */

    public Paths(Drivetrain drive, HookSubsystem hook, LArmSubsystem LArm, RollerIntake intake) {
        m_drive = drive;
        m_hook = hook;
        m_LArm = LArm;   
        m_intake = intake;     
    }

    /**
     * @return
     */

    public Command Path1() { //Motor Show Off
      return new SequentialCommandGroup(
        //new DriveStraight(m_drive, 0.1).withTimeout(5)
        new DriveStraight(m_drive, .2).withTimeout(2)
      );
    }

    /**
     * @return
     */

    public Command Path2() { //Score 'n' Dash
      return new SequentialCommandGroup(
        //new DriveStraight(m_drive, -6, 0.7, true),
        new PushDumpArm(m_LArm),
        new WaitCommand(0.3),
        new PullDumpArm(m_LArm),
        new DriveStraight(m_drive, 36)
      );
    }

    /**
     * @return
     */

    public Command DriveTest() { 
      return new SequentialCommandGroup(
        new PIDTurn(m_drive, 90)
      );
    }
}
 