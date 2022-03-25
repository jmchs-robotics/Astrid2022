package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class Drive {
        //Drivetrain Motor IDs
        public static final int left1ID = 3;
        public static final int left2ID = 5;
        public static final int right1ID = 2;
        public static final int right2ID = 4;

        public static final int kEncoderCPR = 2048;
        public static final double kWheelDiameterInches = 6;
        public static final double kEncoderTicksPerInch = (double) kEncoderCPR / (kWheelDiameterInches * Math.PI);
        public static final double kEncoderInchesPerTick = (kWheelDiameterInches * Math.PI) / (double) kEncoderCPR;

        public static final double kP_turn = 0.8;
        public static final double kI_turn = 0;
	    public static final double kD_turn = 0;
        public static final double kTurnToleranceDeg = 5;
        public static final double kTurnRateToleranceDegPerS = 10; // degrees per second     

        public static final double kP_gyroDriveStraight = 0;

        public static final double kP_forward = 0;
	    public static final double KI_forward = 0;
        public static final double kD_forward = 0;
        
    }

    public static final class Hook {
        //Hook Motor IDs
        public static final int leftHookID = 1;
        public static final int rightHookID = 0;

        public static final double minHookPos = -5; 
        public static final double maxHookPos = 1000000; 
    }
        
    public static final class LArm {
        //Pneumatic Channel IDs 
        public static final int intakeForwardChannel = 1;
        public static final int intakeReverseChannel = 0;
        public static final int dumpForwardChannel = 6;
        public static final int dumpReverseChannel = 7;

        public static final double timeout = 0.1;
    }
    
    public static final class Intake {
        
        public static final int intakeID = 6;

    }

    public static final class Auto {
        
        public static final double wait = 0.25;

    }

}