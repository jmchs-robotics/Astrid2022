package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.cameraserver.*;


public class RobotMap {
	//drivetrain master objects
	public static WPI_TalonFX left1;
    public static WPI_TalonFX left2;
    public static MotorControllerGroup leftMotors;
    public static WPI_TalonFX right1;
    public static WPI_TalonFX right2;
    public static MotorControllerGroup rightMotors;
    public static DifferentialDrive drive;
    
    //control system objects
    public static ADXRS450_Gyro roborioGyro;
    
    //hook objects
    public static WPI_TalonFX leftHookMotor;
    public static WPI_TalonFX rightHookMotor;
    public static MotorControllerGroup hookMotorGroup;

    //clip objects
    public static DoubleSolenoid leftPiston;
    public static DoubleSolenoid rightPiston;

    public static void init() {

    	//Drivetrain master instantiation.
    	//Convention: evens are left motors, odds are right motors.
        left1 = new WPI_TalonFX(Constants.left1ID);
        left2 = new WPI_TalonFX(Constants.left2ID);
        right1 = new WPI_TalonFX(Constants.right1ID);
        right2 = new WPI_TalonFX(Constants.right2ID);
        
        leftMotors = new MotorControllerGroup(left1, left2);
        rightMotors = new MotorControllerGroup(right1, right2);
        
        drive = new DifferentialDrive(leftMotors, rightMotors);
        drive.setSafetyEnabled(true);
        drive.setExpiration(0.1);
        drive.setMaxOutput(1.0);
        
        //instantiate hook motors
        leftHookMotor = new WPI_TalonFX(Constants.leftHookID);
        rightHookMotor = new WPI_TalonFX(Constants.rightHookID);
        hookMotorGroup = new MotorControllerGroup(leftHookMotor, rightHookMotor);
        
        //instantiate gyro. B/c it is an SPI gyroscope, no need for calibration methods yet
        roborioGyro = new ADXRS450_Gyro();
        
        //instantiate clip pistons
        leftPiston = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 4, 5);
        rightPiston = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 6, 7);
        
    }
}
