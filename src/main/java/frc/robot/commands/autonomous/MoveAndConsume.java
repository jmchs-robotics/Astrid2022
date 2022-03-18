package frc.robot.commands.autonomous;

import java.util.TimeZone;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ConsumeCargo;
import frc.robot.commands.DriveStraight;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IntakeSubsystem;

public class MoveAndConsume extends CommandBase{

    IntakeSubsystem m_intake;
    Drivetrain m_drive;
    double inches;
    double w = 0.25;

    /**
     * @param intake
     * @param drive
     * @param inches
     */
    public MoveAndConsume(IntakeSubsystem intake, Drivetrain drive, double time, double inches) {
        m_intake = intake;
        m_drive = drive;
        addRequirements(m_intake, m_drive);

        new SequentialCommandGroup(
            new ParallelRaceGroup(
                new DriveStraight(m_drive, 0.2, inches), 
                new ConsumeCargo(m_intake)
            ).withTimeout(time),
            new WaitCommand(w)
        );
    }    

    public MoveAndConsume(IntakeSubsystem intake, Drivetrain drive, double time) {
        m_intake = intake;
        m_drive = drive;
        addRequirements(m_intake, m_drive);

        new SequentialCommandGroup(
            new ParallelRaceGroup(
                new DriveStraight(m_drive, 0.2),
                new ConsumeCargo(m_intake)
            ).withTimeout(time),
            new WaitCommand(w)
        );
    }   
}
