// Author: UMN Robotics Ri3D
// Last Updated: January 2025

package frc.robot.subsystems;

import frc.robot.Constants.VisionConstants;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {
    PhotonCamera camera = new PhotonCamera(VisionConstants.KCameraName); 
    List<PhotonPipelineResult> results; 
    PhotonPipelineResult result; 
    boolean hasTarget;

    @Override
    public void periodic() {
        results = camera.getAllUnreadResults(); 
        if (!results.isEmpty()) {
            result = results.get(results.size()-1); 
            hasTarget = result.hasTargets();
        }
        SmartDashboard.putBoolean("HasTarget", hasTarget);
    }
    
    public PhotonTrackedTarget getTargetWithID(int id) { 
        List<PhotonTrackedTarget> targets = result.getTargets(); 
        for (PhotonTrackedTarget i : targets) {
            if (i.getFiducialId() == id) { 
                return i; 
            }
        }
        return null; 
    }
    
    public PhotonTrackedTarget getBestTarget() {
        if (hasTarget) {
            return result.getBestTarget(); 
        }
        else {
            return null; 
        }
    }

    public boolean getHasTarget() {
        return hasTarget; 
    }

    public double getDistanceToTarget(PhotonTrackedTarget target) {
        if (!hasTarget) {
            return 0;
        }
        double april_tag_pitch = target.getPitch();

        double distance = PhotonUtils.calculateDistanceToTargetMeters(
            VisionConstants.KCameraHeightMeters,
            VisionConstants.KTargetHeightMeters,
            VisionConstants.KCameraPitchRadian,
            Math.toRadians(target.getPitch())
        );

        SmartDashboard.putNumber("t_distance", distance);
        SmartDashboard.putNumber("t_pitch", april_tag_pitch);
        return distance;
    }

    public boolean InRange(double distanceThreshold, double distanceThresholdRange, double angleThreshold, double angleThresholdRange) {
        if (!hasTarget) {
            return false;
        }
    
        PhotonTrackedTarget bestTarget = getBestTarget();
        double distanceToTarget  = getDistanceToTarget(bestTarget);
        double angleToTarget = bestTarget.getYaw(); 
        double skewTarget = bestTarget.getSkew();

        boolean inRange = Math.abs(Math.abs(distanceToTarget) - distanceThreshold) >= distanceThresholdRange && Math.abs(Math.abs(angleToTarget) - angleThreshold) >= angleThresholdRange;
        
        SmartDashboard.putNumber("t_distance", distanceToTarget);
        SmartDashboard.putNumber("t_angle", angleToTarget);
        SmartDashboard.putNumber("t_skew", skewTarget);
        SmartDashboard.putBoolean("InRange", inRange);
    
        return inRange;
    }
}