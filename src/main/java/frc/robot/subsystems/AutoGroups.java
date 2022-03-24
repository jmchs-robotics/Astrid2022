package frc.robot.subsystems;

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
import frc.robot.commands.ConsumeCargo;
import frc.robot.commands.DriveStraight;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoGroups extends SubsystemBase{
    
    LArmSubsystem m_LArm;
    IntakeSubsystem m_intake;
    Drivetrain m_drive;
    double w = 0.25;

    /**
     * @param intake
     * @param drive
     * @param inches
     */
    public AutoGroups(LArmSubsystem larm, Drivetrain drive, IntakeSubsystem intake) {
        
        m_LArm = larm;
        //addRequirements(m_LArm);

        m_intake = intake;
        //addRequirements(m_intake);

        m_drive = drive;
        //addRequirements(m_drive);
    }

    public SequentialCommandGroup AutoDump()
    {
        return new SequentialCommandGroup(
            new PushDumpArm(m_LArm).withTimeout(0.1),
            new WaitCommand(0.25),
            new PullDumpArm(m_LArm).withTimeout(0.1),
            new WaitCommand(0.25)
        );
    }

    public SequentialCommandGroup DriveToTerminal1(String pos)
    {
        String position = pos;

        if (position.equals("left") || position.equals("right")) {
            return new SequentialCommandGroup(
            	
                new DriveStraight(m_drive, 0.2, 256).withTimeout(4), //change time to seconds it takes to get to terminal from left/right 
                new WaitCommand(w)

            );
        }

        return new SequentialCommandGroup(

            new DriveStraight(m_drive, 0.2, 150).withTimeout(4), //change time to seconds it takes to get to terminal from center 
            new WaitCommand(w)
          
        );
    }

    public SequentialCommandGroup DriveToTerminal2(String pos, double vBus)
    {
        String position = pos;
        double vbus = vBus;

        if (position.equals("left") || position.equals("right")) {
            return new SequentialCommandGroup(
            	
                new DriveStraight(m_drive, vbus, 256).withTimeout(4),
                new WaitCommand(w)

            );
        }
        return new SequentialCommandGroup(

            new DriveStraight(m_drive, vbus, 150).withTimeout(7),
            new WaitCommand(w)
                
        );
    }

    public SequentialCommandGroup MoveAndConsume(double seconds)
    {
        double time = seconds;

        return new SequentialCommandGroup(
            new ParallelRaceGroup(
                new DriveStraight(m_drive, 0.2),
                new ConsumeCargo(m_intake)
            ).withTimeout(time),
            new WaitCommand(w)
        );
    }

}
