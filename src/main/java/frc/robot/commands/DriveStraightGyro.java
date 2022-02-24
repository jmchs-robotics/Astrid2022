package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveStraightGyro extends CommandBase {

	private Drivetrain m_subsystem;

	double endVal;
	double vBus;
	double initialHeading;
	boolean useFeedback;
	double direction;
	double distThisLeg;

	/**
	 * @param subsystem
	 * @param timeToRun
	 * @param percentVBus
	 */
	
  public DriveStraightGyro(Drivetrain subsystem, double timeToRun, double percentVBus) {

		m_subsystem = subsystem;
    	addRequirements(m_subsystem);
    	
    	endVal = timeToRun;
    	vBus = -percentVBus;
    	useFeedback = false;
    }
    
    /**
     * @desc Command that drives straight with the help of encoders
     * @param inches Needs to be negative for backwards movement, positive otherwise.
     * @param percentVBus Requires same sign as inches.
     * @param useEncoders TRUE to use encoders.
     */
    public DriveStraightGyro(Drivetrain subsystem, double inches, double percentVBus, boolean useEncoders) {
    	
		m_subsystem = subsystem;
		addRequirements(m_subsystem);
    	
    	endVal = inches * Drivetrain.kEncoderTicksPerInch;
    	distThisLeg = endVal;
    	vBus = percentVBus;
    	useFeedback = useEncoders;

    	//direction is positive for forwards and negative for backwards.
    	
    }

    // Called just before this Command runs the first time
    public void initialize() {
    	// set our target position as current position plus desired distance
    	endVal += m_subsystem.getRightEncoderPos(0);
    	// get the robot's current direction, so we can stay pointed that way
    	initialHeading = m_subsystem.getGyroHeading();
    
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
    	double proportion = Drivetrain.kPGyroConstant * (m_subsystem.getGyroHeading() - initialHeading);
    	double coefficient = 1;
    	
    	if(useFeedback) {    		
    		// ramp down at the end of this leg of travel
    		// coefficient = Math.pow((endVal - Robot.drivetrain.getRightEncoderPos(0)) / endVal,2/3);  // this won't really work any more because endVal is not relative to resetting encoders to zero    		
    		coefficient = Math.abs( distThisLeg - m_subsystem.getRightEncoderPos(0)) / 2000;    		
    		coefficient = m_subsystem.thresholdVBus(coefficient);
    	}
    	
    	m_subsystem.tankDrive(coefficient * (vBus - proportion), -coefficient * (vBus + proportion));
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
    	if(useFeedback) {
    		// have we gone far enough?
    		if(Math.signum(vBus) < 0) {
    			return m_subsystem.getRightEncoderPos(0) <= endVal;
    		} else {
    			return m_subsystem.getRightEncoderPos(0) >= endVal;
    		}
    		
    	}
		
		return false;
    }
    
    public boolean exposedIsFinished() {
    	return isFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
    	m_subsystem.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
