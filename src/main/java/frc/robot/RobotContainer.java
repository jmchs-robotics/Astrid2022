package frc.robot;

import frc.robot.commands.*;
import frc.robot.commands.autonomous.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

    public static RobotContainer m_robotContainer = new RobotContainer();

    // The robot's subsystems
    public final Drivetrain m_drive = new Drivetrain();
    public final ClipSubsystem m_Clip = new ClipSubsystem();
    public final HookSubsystem m_Hook = new HookSubsystem();
    public final RollerIntake m_Intake = new RollerIntake();

    // Joysticks
    private final XboxController subStick = new XboxController(0);
    private final XboxController driveStick = new XboxController(1);
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

    // The container for the robot. Configures subsystems, OI devices, and commands.
    private RobotContainer() {

        // SmartDashboard Command Buttons
        SmartDashboard.putData("Extend Hook", new ExtendHook(m_Hook));
        SmartDashboard.putData("Retract Hook", new RetractHook(m_Hook));
        SmartDashboard.putData("Push L-Arm", new PushWeakArm(m_Clip));
        SmartDashboard.putData("Pull L-Arm", new PullWeakArm(m_Clip));
        SmartDashboard.putData("Drive Straight", new DriveStraight(m_drive, 0.5));
        SmartDashboard.putData("Turn", new GyroTurn(m_drive, 90, 0.2,0.05));

        configureButtonBindings();

        configureDefaultCommands();
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

        subA.whenHeld(
            new ExtendHook(m_Hook)
        ); 
        subY.whenHeld(
            new RetractHook(m_Hook)
        );  
        subX.whenPressed(
            new PullStrongArm(m_Clip).withTimeout(0.1)
        );
        subB.whenPressed(
            new PushStrongArm(m_Clip).withTimeout(0.1)
        );
        subLB.whenPressed(
            new PullWeakArm(m_Clip).withTimeout(0.1)
        );
        subRB.whenPressed(
            new PushWeakArm(m_Clip).withTimeout(0.1)
        );
        
        //new ExpelCargo(m_Intake)
        //new ConsumeCargo(m_Intake)
    }


    private void configureDefaultCommands() {

        m_drive.setDefaultCommand(new DefaultArcadeDrive(m_drive, driveStick));
        m_Hook.setDefaultCommand(new DefaultHookControl(m_Hook, subStick));
        
    }

    /**
     * @return The driver stick 
     */

    public XboxController getdriveStick() {
        return driveStick;
        }

    /**
     * @return The sub stick
     */

    public XboxController getsubStick() {
        return subStick;
        }

    /**
     * @return Selected autonomous command
     */

    public Command getAutonomousCommand(String a) {
        Paths p = new Paths(m_drive, m_Hook, m_Clip, m_Intake);
        
        //Default set command
        Command autoCommand = new SequentialCommandGroup(p.Path1());

        //Autonomous options
        switch( a) {
        case "1":
            autoCommand = new SequentialCommandGroup(p.Path1());
            break;
        case "2":
            autoCommand = new SequentialCommandGroup(p.Path2());
            break;
        case "3":
            autoCommand = new SequentialCommandGroup(p.Path3());
            break;
        
        }   

        return autoCommand;
    }
}

