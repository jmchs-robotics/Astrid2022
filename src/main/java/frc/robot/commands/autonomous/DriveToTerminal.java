package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.PIDGyroTurn;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.LArmSubsystem;

public class DriveToTerminal extends CommandBase{

    Drivetrain m_drive;
    double w = 0.25;

    /**
     * @param intake
     * @param drive
     * @param inches
     */
    public DriveToTerminal(Drivetrain drive, String position) {
        m_drive = drive;
        addRequirements(m_drive);

        if (position.equals("left") || position.equals("right")) {
            new SequentialCommandGroup(
            	
                new DriveStraight(m_drive, 0.2, 256).withTimeout(4), //change time to seconds it takes to get to terminal from left/right 
                new WaitCommand(w)

            );
        }
        else {
            new SequentialCommandGroup(

                new DriveStraight(m_drive, 0.2, 150).withTimeout(4), //change time to seconds it takes to get to terminal from center 
                new WaitCommand(w)
                
            );
        }
    }    

    public DriveToTerminal(Drivetrain drive, String position, double vbus) {
        m_drive = drive;
        addRequirements(m_drive);

        if (position.equals("left") || position.equals("right")) {
            new SequentialCommandGroup(
            	
                new DriveStraight(m_drive, vbus, 256).withTimeout(4),
                new WaitCommand(w)

            );
        }
        else {
            new SequentialCommandGroup(

                new DriveStraight(m_drive, vbus, 150).withTimeout(7),
                new WaitCommand(w)
                
            );
        }
    } 

}
