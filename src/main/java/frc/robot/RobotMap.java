package frc.robot;
import frc.robot.Constants.Clip;
import frc.robot.Constants.Drive;
import frc.robot.Constants.Hook;
import frc.robot.Constants.Intake;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;

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
    public static AHRS roborioGyro;
    
    //hook objects
    public static WPI_TalonFX leftHookMotor;
    public static WPI_TalonFX rightHookMotor;
    public static MotorControllerGroup bothHooks;
    public static DifferentialDrive hookDrive;

    //intake objects
    public static WPI_VictorSPX rollerMotor;

    //clip objects
    public static DoubleSolenoid dumpPiston;
    public static DoubleSolenoid climbPiston;

    public static void init() {

    	//Drivetrain master instantiation.
    	//Convention: evens are left motors, odds are right motors.
        left1 = new WPI_TalonFX(Drive.left1ID);
        left2 = new WPI_TalonFX(Drive.left2ID);
        right1 = new WPI_TalonFX(Drive.right1ID);
        right2 = new WPI_TalonFX(Drive.right2ID);
        left1.setNeutralMode(NeutralMode.Brake);
        left2.setNeutralMode(NeutralMode.Brake);
        right1.setNeutralMode(NeutralMode.Brake);
        right2.setNeutralMode(NeutralMode.Brake);
     
        leftMotors = new MotorControllerGroup(left1, left2);
        rightMotors = new MotorControllerGroup(right1, right2);
        rightMotors.setInverted(true);
        
        drive = new DifferentialDrive(leftMotors, rightMotors);
        drive.setSafetyEnabled(true);
        drive.setExpiration(0.1);
        drive.setMaxOutput(0.5);
          
        //instantiate hook motors
        leftHookMotor = new WPI_TalonFX(Hook.leftHookID);
        rightHookMotor = new WPI_TalonFX(Hook.rightHookID);
        leftHookMotor.setNeutralMode(NeutralMode.Brake);
        rightHookMotor.setNeutralMode(NeutralMode.Brake);
        rightHookMotor.setInverted(true);
        bothHooks = new MotorControllerGroup(leftHookMotor, rightHookMotor);
        hookDrive = new DifferentialDrive(leftHookMotor, rightHookMotor);      

        //instantiate intake motors
        rollerMotor = new WPI_VictorSPX(Intake.intakeID);         
      
        //instantiate gyro. B/c it is an SPI gyroscope, no need for calibration methods yet
        roborioGyro = new AHRS(SPI.Port.kMXP);
        
        //instantiate clip pistons
        climbPiston = new DoubleSolenoid(0, PneumaticsModuleType.CTREPCM, Clip.climbForwardChannel, Clip.climbReverseChannel);
        dumpPiston = new DoubleSolenoid(0, PneumaticsModuleType.CTREPCM, Clip.dumpForwardChannel, Clip.dumpReverseChannel);
    }
}