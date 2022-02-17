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
    HookSubsystem m_Hook;
    ClipSubsystem m_Clip;
    RollerIntake m_Intake;

    /**
     * @param drive
     * @param hook
     * @param clip
     * @param intake
     */

    public Paths(Drivetrain drive, HookSubsystem hook, ClipSubsystem clip, RollerIntake intake) {
        m_drive = drive;
        m_Hook = hook;
        m_Clip = clip;   
        m_Intake = intake;     
    }

    /**
    * @return
    */

    public Command PathTestCommand() {
      return new SequentialCommandGroup(

      );
    }

    /**
     * @return
     */

    public Command Path1() {
      return new SequentialCommandGroup(

      );
    }

    /**
     * @return
     */

    public Command Path2() {
      return new SequentialCommandGroup(

      );
    }
}
 