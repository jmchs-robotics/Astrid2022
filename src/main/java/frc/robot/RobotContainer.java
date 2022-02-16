package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;



/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

    public static ADXRS450_Gyro roborioGyro;

    private static RobotContainer m_robotContainer = new RobotContainer();

    // The robot's subsystems
    public final Drivetrain m_drive = new Drivetrain();
    public final ClipSubsystem m_Clip = new ClipSubsystem();
    public final HookSubsystem m_Hook = new HookSubsystem();
    public final RollerIntake m_Intake = new RollerIntake();

    // Joysticks
    private final XboxController subStick = new XboxController(1);
    private final XboxController driveStick = new XboxController(0);
    private final JoystickButton driveA = new JoystickButton(driveStick, XboxController.Button.kA.value);
    private final JoystickButton driveB = new JoystickButton(driveStick, XboxController.Button.kB.value);    
    private final JoystickButton driveX = new JoystickButton(driveStick, XboxController.Button.kX.value); 
    private final JoystickButton driveY = new JoystickButton(driveStick, XboxController.Button.kY.value); 
    private final JoystickButton driveLB = new JoystickButton(driveStick, XboxController.Button.kLeftBumper.value);
    private final JoystickButton driveRB = new JoystickButton(driveStick, XboxController.Button.kRightBumper.value);
    private final JoystickButton subA = new JoystickButton(subStick, XboxController.Button.kA.value);    
    private final JoystickButton subB = new JoystickButton(subStick, XboxController.Button.kB.value);   
    private final JoystickButton subX = new JoystickButton(subStick, XboxController.Button.kX.value);  
    private final JoystickButton subY = new JoystickButton(subStick, XboxController.Button.kY.value);  
    private final JoystickButton subLB = new JoystickButton(subStick, XboxController.Button.kLeftBumper.value); 
    private final JoystickButton subRB = new JoystickButton(subStick, XboxController.Button.kRightBumper.value); 
  
    // A chooser for autonomous commands
    SendableChooser<Command> m_chooser = new SendableChooser<>();

    // The container for the robot. Configures subsystems, OI devices, and commands.
    private RobotContainer() {

        // SmartDashboard Buttons
        SmartDashboard.putData("extend hook", new ExtendHook(m_Hook));
        SmartDashboard.putData("retract hook", new RetractHook(m_Hook));
        SmartDashboard.putData("push L-arm", new PushClipArm(m_Clip));
        SmartDashboard.putData("pull L-arm", new PullClipArm(m_Clip));
        SmartDashboard.putData("drive straight", new DriveStraight(m_drive, 0, 0, true));
        SmartDashboard.putData("turn", new Turn(m_drive,0, 0, 0));

        configureButtonBindings();

        configureDefaultCommands();

        // Configure autonomous sendable chooser

        // m_chooser.addOption("auto1", new auto1());
        // m_chooser.setDefaultOption("auto1", new auto1());

        SmartDashboard.putData("Auto Mode", m_chooser);
    }

    public static RobotContainer getInstance() {
        return m_robotContainer;
    }

    private void configureButtonBindings() {
        /*
        driveA.whenPressed(new auto1() ,true);
            SmartDashboard.putData("driveA",new auto1() );
                
        driveB.whenPressed(new auto1() ,true);
            SmartDashboard.putData("driveB",new auto1() );
                
        driveX.whenPressed(new auto1() ,true);
            SmartDashboard.putData("driveX",new auto1() );

        driveY.whenPressed(new auto1() ,true);
            SmartDashboard.putData("driveY",new auto1() );
                
        driveLB.whenPressed(new auto1() ,true);
            SmartDashboard.putData("driveLB",new auto1() );
        
        driveRB.whenPressed(new auto1() ,true);
            SmartDashboard.putData("driveRB",new auto1() );

        */

        subA.whenPressed(
            new RetractHook(m_Hook)
        );   
        subB.whenPressed(
            new PushClipArm(m_Clip)
        );
        subX.whenPressed(
            new PushClipArm(m_Clip)
        );
        subY.whenPressed(
            new ExtendHook(m_Hook)
        );
        subLB.whenPressed(
            new ConsumeCargo(m_Intake)
        );
        subRB.whenPressed(
            new ConsumeCargo(m_Intake)
        );
    }


    private void configureDefaultCommands() {

        m_drive.setDefaultCommand(new DefaultArcadeDrive(m_drive, driveStick));

    }

    public XboxController getdriveStick() {
        return driveStick;
        }

    public XboxController getsubStick() {
        return subStick;
        }


    public Command getAutonomousCommand() {
        // The selected command will be run in autonomous
        return m_chooser.getSelected();
    }
}

