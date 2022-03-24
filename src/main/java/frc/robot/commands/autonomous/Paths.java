package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
import frc.robot.subsystems.AutoGroup;
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
    AutoGroup m_autoGroup;
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
        m_autoGroup = new AutoGroup(m_LArm, m_drive, m_intake);  
    }

    /**
     * @return
     */

    public Command DumpAndRun(String pos) { //Dump and Intake Cargo
      return new SequentialCommandGroup(
        //dump held cargo
        new AutoDump(m_LArm),
        //move to first ball and intake
        new DriveStraight(m_drive, 0.2).withTimeout(4),
        new WaitCommand(w),
        //rotate to the loading station
        new RotateToBall(m_drive, pos),
        //drives distance depending on starting pos
        new DriveToTerminal(m_drive, pos)
      ); 
    }

    /**
     * @return
     */

    public Command Taxi() { //Intake Cargo
      return new SequentialCommandGroup(

        new AutoDump(m_LArm),

        new DriveStraight(m_drive, 0.2).withTimeout(4)
      
      );
    }

    public Command Path3() { //Grab n' Go
      return new SequentialCommandGroup(
        new ParallelCommandGroup(
          new DriveStraight(m_drive, 0.2, 108),
          new ConsumeCargo(m_intake)
        ).withTimeout(2),
        new PIDGyroTurn(m_drive, 75),
        new DriveStraight(m_drive, 0.2, 256)
      );
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
 