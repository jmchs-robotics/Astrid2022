package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
import frc.robot.Constants.Drive;
import frc.robot.commands.*;

/**
 *
 */
public class Paths { // extends CommandBase {
 
    //private SwerveDriveSubsystem m_swerve;
    Drivetrain m_drive;
    HookSubsystem m_hook;
    LArmSubsystem m_LArm;
    IntakeSubsystem m_intake;
    private double w = 0.25;

    /**
     * @param drive
     * @param hook
     * @param clip
     * @param intake
     */

    public Paths(Drivetrain drive, HookSubsystem hook, LArmSubsystem LArm, IntakeSubsystem intake) {
        m_drive = drive;
        m_hook = hook;
        m_LArm = LArm;   
        m_intake = intake;     
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

    public Command IntakeAndTurn(String pos) {
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
          new DriveStraight(m_drive, 0.2, 256).withTimeout(4)
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

    public Command Test() { 
      return new SequentialCommandGroup(
        new PIDGyroTurn(m_drive, 90)
        //new PushDumpArm(m_LArm).withTimeout(0.1)
        //new WaitCommand(w),
        //new MoveAndConsume(m_intake, m_drive, 5, 12)
      );
    }
}
 