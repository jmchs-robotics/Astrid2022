package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 *
 */
public class LArmSubsystem extends SubsystemBase {

    private DoubleSolenoid climbPiston;
    private DoubleSolenoid dumpPiston;
        
    /**
    *
    */
    public LArmSubsystem() {

        climbPiston = RobotMap.climbPiston;
        addChild("strongPiston", climbPiston);
        dumpPiston = RobotMap.dumpPiston;
        addChild("weakPiston", dumpPiston);  

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    public void setDumpSolenoid (Value val) {
        dumpPiston.set(val);
    }

    public void setClimbSolenoid (Value val) {
        climbPiston.set(val);
    }

    public void setBothSolenoids (Value val) {
        dumpPiston.set(val);
        climbPiston.set(val);
    }

    public String getPistonValue() {
        return "Dump: " + dumpPiston.get() + "   Climb: " + climbPiston.get();
    }

}
 
