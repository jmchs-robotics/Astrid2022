package frc.robot.commands;
import edu.wpi.first.wpilibj.simulation.DriverStationSim;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Turn extends CommandBase {
	
	Drivetrain m_subsystem;
	double targetHeading;
	double vBus;
	double threshold;

	/**
	 * Instantiate a turn object. Angle is in gryoscope native units.
	 * @param targetAngle
	 * The gyroscope target units (from robot current heading, not from absolute orientation)
	 * @param percentVBus
	 * The maximum turning voltage bus proportion
	 */
    public Turn(Drivetrain subsystem, double targetAngle, double percentVBus) {
        
		m_subsystem = subsystem;
    	addRequirements(subsystem);
    	
    	targetHeading = targetAngle;
    	vBus = percentVBus;
    }

    // Called just before this Command runs the first time
    public void initialize() {
    	targetHeading += m_subsystem.getGyroHeading(); //accomodate for not actually being square on field. Alternative is zeroing the gyro before any of this.
    	SmartDashboard.putString("Current Command: ", "turn");
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {

    	m_subsystem.tankDrive(vBus, -vBus);
    	SmartDashboard.putNumber("Left gyro val: ", vBus);
    	SmartDashboard.putNumber("Right gyro val", vBus);
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    public void end() {
    	m_subsystem.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    public void interrupted() {
    	end();
    }
}
