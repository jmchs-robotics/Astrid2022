// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Command.

package frc.robot.commands;
import edu.wpi.first.wpilibj.simulation.DriverStationSim;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class driveStraight extends CommandBase {

	double endVal;
	double vBus;
	double initialHeading;
	boolean useFeedback;
	double direction;
	double distThisLeg;
	
    public driveStraight (double timeToRun, double percentVBus) {

    	addRequirements(Robot.drivetrain);
    	
    	endVal = timeToRun;
    	vBus = -percentVBus;
    	useFeedback = false;
    }
    
    //
    /**
     * 
     * @param inches Needs to be negative for backwards movement, positive otherwise.
     * @param percentVBus Requires same sign as inches.
     * @param useEncoders TRUE to use encoders.
     */
    public driveStraight(double inches, double percentVBus, boolean useEncoders) {
    	addRequirements(Robot.drivetrain);
    	
    	endVal = inches * Drivetrain.kEncoderTicksPerInch;
    	distThisLeg = endVal;
    	vBus = percentVBus;
    	useFeedback = useEncoders;

    	//direction is positive for forwards and negative for backwards.
    	
    }

    // Called just before this Command runs the first time
    public void initialize() {
    	// set our target position as current position plus desired distance
    	endVal += Robot.drivetrain.getRightEncoderPos(0);
    	// get the robot's current direction, so we can stay pointed that way
    	initialHeading = Robot.drivetrain.getGyroHeading();
    
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
    	double proportion = Drivetrain.kPGyroConstant * (Robot.drivetrain.getGyroHeading() - initialHeading);
    	double coefficient = 1;
    	
    	if(useFeedback) {    		
    		// ramp down at the end of this leg of travel
    		// coefficient = Math.pow((endVal - Robot.drivetrain.getRightEncoderPos(0)) / endVal,2/3);  // this won't really work any more because endVal is not relative to resetting encoders to zero    		
    		coefficient = Math.abs( distThisLeg - Robot.drivetrain.getRightEncoderPos(0)) / 2000;    		
    		coefficient = Robot.drivetrain.thresholdVBus(coefficient);
    	}
    	
    	Robot.drivetrain.tankDrive(coefficient * (vBus - proportion), -coefficient * (vBus + proportion));
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
    	if(useFeedback) {
    		// have we gone far enough?
    		if(Math.signum(vBus) < 0) {
    			return Robot.drivetrain.getRightEncoderPos(0) <= endVal;
    		} else {
    			return Robot.drivetrain.getRightEncoderPos(0) >= endVal;
    		}
    		
    	}
		
		return false;
    }
    
    public boolean exposedIsFinished() {
    	return isFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}