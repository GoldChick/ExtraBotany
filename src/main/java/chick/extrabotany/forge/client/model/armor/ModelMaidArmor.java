package chick.extrabotany.forge.client.model.armor;


import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelMaidArmor
{
    public static MeshDefinition createInsideMesh()
    {
        MeshDefinition mesh = new MeshDefinition();
        var root = mesh.getRoot();

        root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);

        var body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        body.addOrReplaceChild("a1", CubeListBuilder.create().texOffs(20, 48).mirror().addBox(0.0F, 0.0F, 0.0F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 9.0F, 1.0F, 0.3491F, 0.0F, 0.0F));
        body.addOrReplaceChild("a2", CubeListBuilder.create().texOffs(12, 38).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 9.0F, -2.0F, 0.0F, 0.0F, 0.3491F));
        body.addOrReplaceChild("a3", CubeListBuilder.create().texOffs(1, 48).mirror().addBox(0.0F, 0.0F, 0.0F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 9.0F, -2.0F, -0.3491F, 0.0F, 0.0F));
        body.addOrReplaceChild("a4", CubeListBuilder.create().texOffs(1, 38).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 9.0F, -2.0F, 0.0F, 0.0F, -0.3491F));
        body.addOrReplaceChild("b1", CubeListBuilder.create().texOffs(16, 32).mirror().addBox(0.0F, 0.0F, 0.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 9.0F, 1.0F, 0.5236F, 0.0F, 0.0F));
        body.addOrReplaceChild("b2", CubeListBuilder.create().texOffs(1, 32).mirror().addBox(0.0F, 0.0F, 0.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 9.0F, -2.0F, -0.5236F, 0.0F, 0.0F));

        var bootL = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.39F, 8.5F, -2.49F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));
        bootL.addOrReplaceChild("bootL", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
        var bootR = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 8.5F, -2.51F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offset(-2.0F, 12.0F, 0.0F));
        bootR.addOrReplaceChild("bootR", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
        return mesh;
    }

    public static MeshDefinition createOutsideMesh()
    {
        MeshDefinition mesh = new MeshDefinition();
        var root = mesh.getRoot();

        root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        var head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("fake_hat", CubeListBuilder.create().texOffs(1, 61).mirror().addBox(0.0F, 0.0F, 0.0F, 10.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, -9.0F, -2.0F));
        head.addOrReplaceChild("ear1", CubeListBuilder.create().texOffs(1, 55).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-5.0F, -8.5F, -3.0F, 0.0F, 0.0F, -0.9076F));
        head.addOrReplaceChild("ear2", CubeListBuilder.create().texOffs(12, 55).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(3.0F, -10.8F, -3.0F, 0.0F, 0.0F, 0.9076F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition bodyAnchor = body.addOrReplaceChild("bodyAnchor", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
        bodyAnchor.addOrReplaceChild("oupai", CubeListBuilder.create().texOffs(1, 67).mirror().addBox(0.0F, 0.0F, 0.0F, 6.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 1.0F, -4.0F, 0.1745F, 0.0F, 0.0F));

        var armLAnchor = root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(4.0F, 2.0F, 0.0F));
        armLAnchor.addOrReplaceChild("left", CubeListBuilder.create().texOffs(1, 75).mirror().addBox(0.0F, 0.0F, 0.0F, 5.0F, 4.0F, 6.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-1.0F, -2.0F, -3.0F));
        var armRAnchor = root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-4.0F, 2.0F, 0.0F));
        armRAnchor.addOrReplaceChild("right", CubeListBuilder.create().texOffs(24, 75).mirror().addBox(0.0F, 0.0F, 0.0F, 5.0F, 4.0F, 6.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-4.0F, -2.0F, -3.0F));

        var bootL = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.39F, 8.5F, -2.49F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));
        bootL.addOrReplaceChild("bootL", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
        var bootR = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 8.5F, -2.51F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offset(-2.0F, 12.0F, 0.0F));
        bootR.addOrReplaceChild("bootR", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
        return mesh;
    }
}