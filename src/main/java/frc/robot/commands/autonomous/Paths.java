package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
import frc.robot.subsystems.AutoGroup;
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
     * @param clip
     * @param intake
     */

    public Paths(Drivetrain drive, HookSubsystem hook, LArmSubsystem LArm, IntakeSubsystem intake, AutoGroup auto) {
        m_drive = drive;
        m_hook = hook;
        m_LArm = LArm;   
        m_intake = intake;
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

        new PushDumpArm(m_LArm).withTimeout(0.1),
        new WaitCommand(w),
        new PullDumpArm(m_LArm).withTimeout(0.1),
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
          new PushDumpArm(m_LArm).withTimeout(0.1),
          new WaitCommand(w),
          new PullDumpArm(m_LArm).withTimeout(0.1),
          new WaitCommand(w),

          //Move and Intake a ball
          new ParallelCommandGroup(
            new DriveStraight(m_drive, 0.2).withTimeout(3),
            new ConsumeCargo(m_intake).withTimeout(3)
          ),

          new WaitCommand(w),
          new PIDGyroTurn(m_drive, -70),

          new WaitCommand(w),
          new DriveStraight(m_drive, 0.2, 256).withTimeout(8)
        );
      } 
      
      else if (pos.equals("right")) {
        return new SequentialCommandGroup(
          //Auto dump
          new PushDumpArm(m_LArm).withTimeout(0.1),
          new WaitCommand(w),
          new PullDumpArm(m_LArm).withTimeout(0.1),
          new WaitCommand(w),

          //Move and Intake a ball
          new ParallelCommandGroup(
            new DriveStraight(m_drive, 0.2).withTimeout(3),
            new ConsumeCargo(m_intake).withTimeout(3)
          ),

          new WaitCommand(w),
          new PIDGyroTurn(m_drive, 70),

          new WaitCommand(2),
          new DriveStraight(m_drive, 0.2, 256).withTimeout(4)
        );
      } 
      
      else {
        return new SequentialCommandGroup(
          //Auto dump
          new PushDumpArm(m_LArm).withTimeout(0.1),
          new WaitCommand(w),
          new PullDumpArm(m_LArm).withTimeout(0.1),
          new WaitCommand(w),

          //Move and Intake a ball
          new ParallelCommandGroup(
            new DriveStraight(m_drive, 0.2).withTimeout(3),
            new ConsumeCargo(m_intake).withTimeout(3)
          ),

          new WaitCommand(w),
          new PIDGyroTurn(m_drive, 15)
        );
      }
    }

    /**
     * @return
     */

    public Command AutoScore(String pos) {
      return new SequentialCommandGroup(
        m_auto.AutoDump(),
        m_auto.MoveAndConsume(5),
        m_auto.RotateToBall(pos),
        m_auto.DriveToTerminal(pos)
      );
    }

    public Command TaxiAndIntake(String pos) {
      return new SequentialCommandGroup(
        m_auto.MoveAndConsume(3)
      );
    }

    public Command Test() { 
      return new SequentialCommandGroup(
        new PIDGyroTurn(m_drive, 90)
        //new PushDumpArm(m_LArm).withTimeout(0.1)
        //new WaitCommand(w),
        //new MoveAndConsume(m_intake, m_drive, 5, 12)
      );
    }
}
 