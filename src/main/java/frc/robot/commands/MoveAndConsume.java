package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.RollerIntake;

public class MoveAndConsume extends CommandBase{

    RollerIntake m_intake;
    Drivetrain m_drive;
    double inches;

    /**
     * @param intake
     * @param drive
     * @param inches
     */
    public MoveAndConsume(RollerIntake intake, Drivetrain drive, double inches) {
        m_intake = intake;
        m_drive = drive;
        addRequirements(m_intake, m_drive);

        new ParallelCommandGroup(
            new DriveStraight(m_drive, 0.2, inches),
            new ConsumeCargo(m_intake)
        );
    }    
}
