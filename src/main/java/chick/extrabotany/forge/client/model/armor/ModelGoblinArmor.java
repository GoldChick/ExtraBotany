package chick.extrabotany.forge.client.model.armor;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class ModelGoblinArmor
{
    public static MeshDefinition createInsideMesh()
    {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
        PartDefinition leftArm = root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
        PartDefinition rightArm = root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(112, 112).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(108, 61).mirror().addBox(-2.5F, -0.5F, -2.5F, 5.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(98, 61).mirror().addBox(-2.1F, 1.0F, -3.5F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(84, 70).mirror().addBox(-3.1F, 2.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(112, 112).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(108, 61).addBox(-2.5F, -0.5F, -2.5F, 5.0F, 13.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(98, 61).addBox(-2.0F, 1.0F, -3.5F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(84, 70).addBox(-3.0F, 2.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
        root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        return mesh;
    }

    public static MeshDefinition createOutsideMesh()
    {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 18).addBox(-2.0F, -1.0F, -5.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(4, 23).addBox(-1.5F, -6.0F, -5.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 23).addBox(0.5F, -6.0F, -5.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.5F, -8.5F, -4.5F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("plates", CubeListBuilder.create().texOffs(0, 49).addBox(-3.0F, -0.5F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 56).addBox(-3.0F, -1.5F, -2.0F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 65).addBox(-1.0F, -2.5F, 1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 74).addBox(-3.0F, -0.5F, 3.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 69).addBox(-4.0F, -0.5F, -1.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 62).addBox(-4.0F, -2.5F, -1.0F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 35).addBox(-2.0F, 1.5F, -4.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(-1.5F, 2.5F, -3.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 42).addBox(-2.5F, 0.5F, -3.5F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -2.0F, 0.0F, 0.7854F, 0.0F));
        head.addOrReplaceChild("bang2", CubeListBuilder.create().texOffs(0, 22).addBox(-0.75F, -6.25F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, -5.0F, 0.0F, 0.0F, -0.1745F));
        head.addOrReplaceChild("bang1", CubeListBuilder.create().texOffs(12, 22).addBox(-0.25F, -6.25F, -1.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, -4.0F, 0.0F, 0.0F, 0.1745F));
        head.addOrReplaceChild("ear", CubeListBuilder.create().texOffs(16, 18).addBox(-1.0F, -1.0F, -2.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(16, 24).addBox(-11.0F, -1.0F, -2.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -4.0F, 0.0F, 0.7854F, 0.0F, 0.0F));
        head.addOrReplaceChild("hair", CubeListBuilder.create().texOffs(35, 0).addBox(-1.5F, -0.5F, 0.25F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.5F, 2.0F, 0.1745F, -0.3491F, 0.0F));
        head.addOrReplaceChild("hair2", CubeListBuilder.create().texOffs(34, 4).addBox(-1.25F, -0.5F, 0.0F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -9.5F, 2.25F, -0.3491F, 0.2618F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(100, 0).addBox(-4.5F, -0.5F, -2.5F, 9.0F, 13.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(50, 8).addBox(-5.0F, 6.0F, -3.0F, 10.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(68, 0).addBox(-5.0F, 11.0F, -3.0F, 10.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(110, 18).addBox(-4.0F, 0.0F, 2.25F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
        body.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(50, 0).addBox(5.5F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(82, 8).addBox(-1.5F, -2.5F, -1.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(54, 0).addBox(-0.5F, -5.5F, -2.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(96, 18).addBox(-3.5F, -2.5F, 5.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(46, 9).addBox(0.0F, -6.5F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 15).addBox(0.5F, -4.5F, -3.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(60, 15).addBox(2.5F, -2.5F, -3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(92, 8).addBox(-5.5F, 2.5F, -2.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, -2.5F, 0.0F, 0.0F, -0.7854F));

        PartDefinition bone3 = body.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(66, 15).addBox(-5.0F, -1.0F, -4.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(66, 15).addBox(-5.0F, -1.0F, 3.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(66, 21).addBox(-6.0F, -1.0F, -3.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 0.0F, 0.2618F));
        bone3.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(66, 18).addBox(-5.0F, -0.25F, -4.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(66, 18).addBox(-5.0F, -0.25F, 3.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(80, 21).addBox(-6.0F, -0.25F, -3.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

        PartDefinition bone2 = body.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(78, 15).addBox(0.0F, -1.0F, -4.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(78, 15).addBox(0.0F, -1.0F, 3.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(66, 21).addBox(5.0F, -1.0F, -3.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, 0.0F, 0.0F, 0.0F, -0.2618F));
        bone2.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(78, 18).addBox(0.0F, -0.25F, -4.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(78, 18).addBox(0.0F, -0.25F, 3.0F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(80, 21).addBox(5.0F, -0.25F, -3.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));
        var leftArm = root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);

        PartDefinition left_arm = leftArm.addOrReplaceChild("left_arm", CubeListBuilder.create()
                        .texOffs(108, 48).addBox(-3.75F, 2.5F, -2.5F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
                        .texOffs(98, 48).addBox(-4.25F, 3.0F, -2.0F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                , PartPose.offsetAndRotation(2.5F, 2F, 0.0F, 0F, 0F, 0F));
        left_arm.addOrReplaceChild("bone5", CubeListBuilder.create()
                        .texOffs(100, 25).addBox(-5.25F, -4.0F, -3.5F, 7.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
                , PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));
        left_arm.addOrReplaceChild("handL", CubeListBuilder.create()
                        .texOffs(104, 37).addBox(-4.0F, 0.0F, -3.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                , PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));
        var rightArm = root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);

        PartDefinition right_arm = rightArm.addOrReplaceChild("right_arm", CubeListBuilder.create()
                        .texOffs(108, 48).mirror().addBox(-1.25F, 2.5F, -2.5F, 5.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                        .texOffs(98, 48).mirror().addBox(3.25F, 3.0F, -2.0F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                , PartPose.offset(-2.5F, 2.0F, 0.0F));
        right_arm.addOrReplaceChild("bone4", CubeListBuilder.create()
                        .texOffs(100, 25).mirror().addBox(-1.75F, -4.0F, -3.5F, 7.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
                , PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

        right_arm.addOrReplaceChild("handR", CubeListBuilder.create()
                        .texOffs(104, 37).mirror().addBox(-2.0F, 0.0F, -3.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
                , PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));


        var rightLeg = root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        var leftLeg = root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
        PartDefinition bootL = leftLeg.addOrReplaceChild("bootL", CubeListBuilder.create().texOffs(24, 31).addBox(-3.0F, 8.0F, -3.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(24, 42).addBox(-3.0F, 6.0F, 2.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 45).addBox(2.0F, 6.0F, -1.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(24, 45).addBox(-3.0F, 6.0F, -1.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(29, 45).addBox(2.0F, 7.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(29, 45).addBox(-3.0F, 7.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 27).addBox(-2.0F, 10.0F, -4.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(21, 33).addBox(-1.0F, 9.0F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition bootR = rightLeg.addOrReplaceChild("bootR", CubeListBuilder.create().texOffs(24, 31).mirror().addBox(-3.0F, 8.0F, -3.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(24, 42).mirror().addBox(-3.0F, 6.0F, 2.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(24, 45).mirror().addBox(-3.0F, 6.0F, -1.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(24, 45).mirror().addBox(2.0F, 6.0F, -1.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(29, 45).mirror().addBox(-3.0F, 7.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(29, 45).mirror().addBox(2.0F, 7.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(24, 27).mirror().addBox(-2.0F, 10.0F, -4.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(21, 33).mirror().addBox(-1.0F, 9.0F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
        root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);

        return mesh;
    }
}
