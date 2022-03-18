package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.PIDGyroTurn;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.LArmSubsystem;

public class RotateToBall extends CommandBase{

    Drivetrain m_drive;
    double w = 0.25;

    /**
     * @param intake
     * @param drive
     * @param inches
     */
    public RotateToBall(Drivetrain drive, String position) {
        m_drive = drive;
        addRequirements(m_drive);

        if (position.equals("left")) {
            new SequentialCommandGroup(
            	
                new PIDGyroTurn(m_drive, -70), //angle needs to be measured
                new WaitCommand(w)

            );
        }
        else if (position.equals("right")) {
            new SequentialCommandGroup(

                new PIDGyroTurn(m_drive, 70), //angle needs to be meassured
                new WaitCommand(w)

            );
        }
        else {
            new SequentialCommandGroup(

                new PIDGyroTurn(m_drive, 15), //angle needs to be meassured
                new WaitCommand(w)
                
            );
        }
    }    

}
