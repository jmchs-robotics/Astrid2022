package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
import frc.util.AutoGroup;
import frc.robot.Constants.Auto;
import frc.robot.Constants.Drive;
import frc.robot.commands.*;

/**
 *
 */
public class Paths { // extends CommandBase {
 
    //private SwerveDriveSubsystem m_swerve;
    private Drivetrain m_drive;
    private HookSubsystem m_hook;
    private LArmSubsystem m_LArm;
    private IntakeSubsystem m_intake;
    private AutoGroup m_auto;
    private final double w = Auto.wait;

    /**
     * @param drive
     * @param hook
     * @param LArm
     * @param intake
     */

    public Paths(Drivetrain drive, HookSubsystem hook, LArmSubsystem LArm, AutoGroup auto) {
        m_drive = drive;
        m_hook = hook;
        m_LArm = LArm;
        m_auto = auto;
    }

    /**
     * @return
     */

    public Command Taxi() {
      return new SequentialCommandGroup(
      
        new DriveStraight(m_drive, 0.2).withTimeout(3)

      ); 
    }

    /**
     * @return
     */

    public Command Dump() { //Intake Cargo
      return new SequentialCommandGroup(

        new PushArms(m_LArm).withTimeout(0.1),
        new WaitCommand(w),
        new PullArms(m_LArm).withTimeout(0.1),
        new WaitCommand(w),

        new DriveStraight(m_drive, 0.2).withTimeout(3)
      
      );
    }

    /**
     * @return
     */

    public Command ManualScore(String pos) {
      if (pos.equals("left")) {
        return new SequentialCommandGroup(
          //Auto dump
          new PushArms(m_LArm).withTimeout(0.1),
          new WaitCommand(w),
          new PullArms(m_LArm).withTimeout(0.1),
          new WaitCommand(w),

          new DriveStraight(m_drive, 0.2).withTimeout(3),
          new WaitCommand(w),
          
          new PIDGyroTurn(m_drive, -70),
          new WaitCommand(w),

          new DriveStraight(m_drive, 0.2, 256).withTimeout(8)
        );
      } 
      
      else if (pos.equals("right")) {
        return new SequentialCommandGroup(
          //Auto dump
          new PushArms(m_LArm).withTimeout(0.1),
          new WaitCommand(w),
          new PullArms(m_LArm).withTimeout(0.1),
          new WaitCommand(w),

          new DriveStraight(m_drive, 0.2).withTimeout(3),
          new WaitCommand(w),

          new PIDGyroTurn(m_drive, 70),
          new WaitCommand(w),

          new DriveStraight(m_drive, 0.2, 256).withTimeout(4)
        );
      } 
      
      else {
        return new SequentialCommandGroup(
          //Auto dump
          new PushArms(m_LArm).withTimeout(0.1),
          new WaitCommand(w),
          new PullArms(m_LArm).withTimeout(0.1),
          new WaitCommand(w),

          new DriveStraight(m_drive, 0.2).withTimeout(3),
          new WaitCommand(w),

          new PIDGyroTurn(m_drive, 15),
          new WaitCommand(w),

          new WaitCommand(w),
          new DriveStraight(m_drive, 0.2, 256).withTimeout(4)
        );
      }
    }

    /**
     * @return
     */

    public Command AutoScore(String pos) {
      return new SequentialCommandGroup(
        m_auto.AutoDumpTaxi(),
        new DriveStraight(m_drive, 0.2).withTimeout(5),
        new WaitCommand(w),
        m_auto.RotateToBall(pos),
        m_auto.DriveToTerminal(pos)
      );
    }

    public Command Test() { 
      return new SequentialCommandGroup(
        m_auto.AutoDumpTaxi()
      );
    }

    /*
    public Command Mutation(double seconds, double inch) {
      m_auto.ScoreAndTurn(),
      m_auto.MoveAndConsume(seconds),
      m_auto.FlipReturn(seconds, inch)
    }*/
}
 