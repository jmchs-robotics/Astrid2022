package frc.util;

import java.util.TimeZone;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.PIDGyroTurn;
import frc.robot.commands.PullDumpArm;
import frc.robot.commands.PushDumpArm;
import frc.robot.subsystems.LArmSubsystem;
import frc.robot.Constants.Auto;
import frc.robot.commands.IntakeCargo;
import frc.robot.commands.DriveStraight;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoGroup extends SubsystemBase{
    
    private LArmSubsystem m_LArm;
    private IntakeSubsystem m_intake;
    private Drivetrain m_drive;
    private final double w = Auto.wait;

    /**
     * @param intake
     * @param drive
     * @param inches
     */
    public AutoGroup(LArmSubsystem larm, Drivetrain drive, IntakeSubsystem intake) {
        
        m_LArm = larm;
        m_intake = intake;
        m_drive = drive;

    }

    public SequentialCommandGroup AutoDump() {
        return new SequentialCommandGroup(
            new PushDumpArm(m_LArm).withTimeout(0.1),
            new WaitCommand(w),
            new PullDumpArm(m_LArm).withTimeout(0.1),
            new WaitCommand(w)
        );
    }

    public SequentialCommandGroup DriveToTerminal(String pos) {
        String position = pos;

        if (position.equals("left") || position.equals("right")) {
            return new SequentialCommandGroup(
            	
                new DriveStraight(m_drive, 0.2, 256).withTimeout(8), //change time to seconds it takes to get to terminal from left/right 
                new WaitCommand(w)

            );
        }

        return new SequentialCommandGroup(

            new DriveStraight(m_drive, 0.2, 150).withTimeout(5), //change time to seconds it takes to get to terminal from center 
            new WaitCommand(w)
          
        );
    }

    public SequentialCommandGroup DriveToTerminal(String pos, double vBus)
    {
        String position = pos;
        double vbus = vBus;

        if (position.equals("left") || position.equals("right")) {
            return new SequentialCommandGroup(
            	
                new DriveStraight(m_drive, vbus, 256).withTimeout(8),
                new WaitCommand(w)

            );
        }
        return new SequentialCommandGroup(

            new DriveStraight(m_drive, vbus, 150).withTimeout(5),
            new WaitCommand(w)
                
        );
    }

    public SequentialCommandGroup MoveAndConsume(double seconds)
    {
        double time = seconds;

        return new SequentialCommandGroup(
            new ParallelRaceGroup(
                new DriveStraight(m_drive, 0.2),
                new IntakeCargo(m_intake)
            ).withTimeout(time),
            new WaitCommand(w)
        );
    }

    public SequentialCommandGroup MoveAndConsume(double seconds, double inch)
    {
        double time = seconds;
        double inches = inch;

        return new SequentialCommandGroup(
            new ParallelRaceGroup(
                new DriveStraight(m_drive, 0.2, inches),
                new IntakeCargo(m_intake)
            ).withTimeout(time),
            new WaitCommand(w)
        );
    }

    public SequentialCommandGroup RotateToBall(String pos)
    {
        String position = pos;

        if (position.equals("left")) {
            return new SequentialCommandGroup(
            	
                new PIDGyroTurn(m_drive, -70), //angle needs to be measured
                new WaitCommand(w)

            );
        }
        else if (position.equals("right")) {
            return new SequentialCommandGroup(

                new PIDGyroTurn(m_drive, 70), //angle needs to be meassured
                new WaitCommand(w)

            );
        }
        return new SequentialCommandGroup(

            new PIDGyroTurn(m_drive, 15), //angle needs to be meassured
            new WaitCommand(w)
                
        );
    }

}
