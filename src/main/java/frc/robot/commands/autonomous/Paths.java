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
    ClipSubsystem m_clip;
    RollerIntake m_intake;

    /**
     * @param drive
     * @param hook
     * @param clip
     * @param intake
     */

    public Paths(Drivetrain drive, HookSubsystem hook, ClipSubsystem clip, RollerIntake intake) {
        m_drive = drive;
        m_hook = hook;
        m_clip = clip;   
        m_intake = intake;     
    }

    /**
     * @return
     */

    public Command Path1() { //Move out of the zone
      return new SequentialCommandGroup(
        //new DriveStraight(m_drive, 0.1).withTimeout(5)
        new DriveStraight(m_drive, .2).withTimeout(2)
      );
    }

    /**
     * @return
     */

    public Command Path2() { //Score --> Move
      return new SequentialCommandGroup(
        //new DriveStraight(m_drive, -6, 0.7, true),
        new PushClipArm(m_clip),
        new WaitCommand(0.3),
        new PullClipArm(m_clip),
        new DriveStraight(m_drive, 36)
      );
    }

    /**
     * @return
     */

    public Command Path3() { //Score --> Intake --> Score --> etc.
      return new SequentialCommandGroup(

      );
    }
}
 