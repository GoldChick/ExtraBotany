package chick.extrabotany.forge.client.model.armor;


import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelMikuArmor
{
    public static MeshDefinition createInsideMesh()
    {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);

        var body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        body.addOrReplaceChild("a1", CubeListBuilder.create().texOffs(0, 65).mirror().addBox(0.0F, 0.0F, 0.0F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 9.0F, -2.0F, -0.2618F, 0.0F, 0.0F));
        body.addOrReplaceChild("a2", CubeListBuilder.create().texOffs(15, 65).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(2.7F, 9.0F, -2.0F, -0.2618F, -0.5236F, 0.0F));
        body.addOrReplaceChild("a3", CubeListBuilder.create().texOffs(24, 65).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-2.2F, 9.0F, -1.0F, 0.2618F, 3.6652F, 0.0F));
        body.addOrReplaceChild("b3", CubeListBuilder.create().texOffs(75, 65).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-4.5F, 9.0F, -0.5F, 0.2618F, -0.5236F, 0.0F));
        body.addOrReplaceChild("b1", CubeListBuilder.create().texOffs(51, 65).mirror().addBox(0.0F, 0.0F, 0.0F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 9.0F, 1.0F, 0.2618F, 0.0F, 0.0F));
        body.addOrReplaceChild("b2", CubeListBuilder.create().texOffs(66, 65).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(5.5F, 9.0F, 0.5F, -0.2618F, 3.6652F, 0.0F));
        body.addOrReplaceChild("c1", CubeListBuilder.create().texOffs(42, 65).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(3.0F, 9.0F, -2.0F, 0.0F, 0.0F, -0.3491F));
        body.addOrReplaceChild("c2", CubeListBuilder.create().texOffs(33, 65).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 9.0F, -2.0F, 0.0F, 0.0F, 0.3491F));
        return mesh;
    }

    public static MeshDefinition createOutsideMesh()
    {

        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        var head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("fake_hat", CubeListBuilder.create().texOffs(36, 33).mirror().addBox(0.0F, 0.0F, 0.0F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-5.0F, -9.0F, -1.3F));
        head.addOrReplaceChild("lefthair", CubeListBuilder.create().texOffs(0, 33).mirror().addBox(0.0F, 0.0F, 0.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(2.5F, -9.0F, -1.0F, 0.2618F, 0.0F, -0.4014F));
        head.addOrReplaceChild("righthair", CubeListBuilder.create().texOffs(9, 33).mirror().addBox(0.0F, 0.0F, 0.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-4.5F, -9.8F, 1.0F));
        head.addOrReplaceChild("circle1", CubeListBuilder.create().texOffs(27, 33).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-5.5F, -10.0F, -1.0F, 0.0F, 0.0F, 0.1047F));
        head.addOrReplaceChild("circle2", CubeListBuilder.create().texOffs(18, 33).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(4.5F, -10.0F, -1.0F, 0.0F, 0.0F, -0.1047F));

        var body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        var bodyAnchor = body.addOrReplaceChild("bodyAnchor", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
        bodyAnchor.addOrReplaceChild("oupai", CubeListBuilder.create().texOffs(0, 48).mirror().addBox(0.0F, 0.0F, 0.0F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 2.0F, -4.0F, 0.6109F, 0.0F, 0.0F));
        bodyAnchor.addOrReplaceChild("pifeng", CubeListBuilder.create().texOffs(65, 48).mirror().addBox(0.0F, 0.0F, 0.0F, 8.0F, 12.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 0.0F, 2.0F, 0.1396F, 0.0F, 0.0F));

        PartDefinition Shape1 = body.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(19, 48).mirror().addBox(0.0F, 0.0F, 0.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -3.0F, -3.0F, 0.0F, 0.0F, 0.1745F));
        PartDefinition Shape2 = body.addOrReplaceChild("Shape2", CubeListBuilder.create().texOffs(42, 48).mirror().addBox(0.0F, 0.0F, 0.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-4.0F, -2.0F, -3.0F, 0.0F, 0.0F, -0.1745F));

        root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(4.0F, 2.0F, 0.0F));
        root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-4.0F, 2.0F, 0.0F));

        PartDefinition pantsAnchor = root.addOrReplaceChild("pantsAnchor", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        pantsAnchor.addOrReplaceChild("a1", CubeListBuilder.create().texOffs(0, 65).mirror().addBox(0.0F, 0.0F, 0.0F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 9.0F, -2.0F, -0.2618F, 0.0F, 0.0F));
        pantsAnchor.addOrReplaceChild("a2", CubeListBuilder.create().texOffs(15, 65).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(2.7F, 9.0F, -2.0F, -0.2618F, -0.5236F, 0.0F));
        pantsAnchor.addOrReplaceChild("a3", CubeListBuilder.create().texOffs(24, 65).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-2.2F, 9.0F, -1.0F, 0.2618F, 3.6652F, 0.0F));
        pantsAnchor.addOrReplaceChild("b3", CubeListBuilder.create().texOffs(75, 65).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-4.5F, 9.0F, -0.5F, 0.2618F, -0.5236F, 0.0F));
        pantsAnchor.addOrReplaceChild("b1", CubeListBuilder.create().texOffs(51, 65).mirror().addBox(0.0F, 0.0F, 0.0F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 9.0F, 1.0F, 0.2618F, 0.0F, 0.0F));
        pantsAnchor.addOrReplaceChild("b2", CubeListBuilder.create().texOffs(66, 65).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(5.5F, 9.0F, 0.5F, -0.2618F, 3.6652F, 0.0F));
        pantsAnchor.addOrReplaceChild("c1", CubeListBuilder.create().texOffs(42, 65).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(3.0F, 9.0F, -2.0F, 0.0F, 0.0F, -0.3491F));
        pantsAnchor.addOrReplaceChild("c2", CubeListBuilder.create().texOffs(33, 65).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 9.0F, -2.0F, 0.0F, 0.0F, 0.3491F));

        var bootL = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.39F, 8.5F, -2.49F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));
        bootL.addOrReplaceChild("legLeft", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
        var bootR = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 8.5F, -2.51F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offset(-2.0F, 12.0F, 0.0F));
        bootR.addOrReplaceChild("legRight", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
        return mesh;
    }


}