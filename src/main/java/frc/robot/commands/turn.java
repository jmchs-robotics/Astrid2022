package frc.robot.commands;
import edu.wpi.first.wpilibj.simulation.DriverStationSim;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class turn extends CommandBase {
	double targetHeading;
	double vBus;
	double threshold;
	double error;
	
	/**
	 * Instantiate a turn object. Angle is in gryoscope native units.
	 * @param targetAngle
	 * The gyroscope target units (from robot current heading, not from absolute orientation)
	 * @param percentVBus
	 * The maximum turning voltage bus proportion
	 * @param marginOfError
	 * The allowable error to end the command
	 */
    public turn(double targetAngle, double percentVBus, double marginOfError) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	addRequirements(Robot.drivetrain);
    	
    	targetHeading = targetAngle;
    	vBus = percentVBus;
    	threshold = marginOfError;
    }

    // Called just before this Command runs the first time
    public void initialize() {
    	targetHeading += Robot.drivetrain.getGyroHeading(); //accomodate for not actually being square on field. Alternative is zeroing the gyro before any of this.
    	SmartDashboard.putNumber("Turn ME: ", threshold);
    	SmartDashboard.putString("Current Command: ", "turn");
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
    	error = (targetHeading - Robot.drivetrain.getGyroHeading()) * Drivetrain.kPGyroTurnConstant;
    	
    	double leftVal = 1.5 * Robot.drivetrain.thresholdVBus(vBus * error);
    	double rightVal = 1.5 * Robot.drivetrain.thresholdVBus(vBus * error);
    	
    	Robot.drivetrain.tankDrive(leftVal, rightVal);
    	SmartDashboard.putNumber("Left gyro val: ", leftVal);
    	SmartDashboard.putNumber("Right gyro val", rightVal);
    	
    	SmartDashboard.putNumber("turn error: ", error);
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
        return error < threshold && error > -threshold;
    }

    // Called once after isFinished returns true
    public void end() {
    	Robot.drivetrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    public void interrupted() {
    	end();
    }
}