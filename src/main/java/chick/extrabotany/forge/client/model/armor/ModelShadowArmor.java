package chick.extrabotany.forge.client.model.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ModelShadowArmor
{
    public static MeshDefinition createInsideMesh()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition root = meshdefinition.getRoot();

        root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);

        PartDefinition pantsAnchor = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition f1 = pantsAnchor.addOrReplaceChild("f1", CubeListBuilder.create().texOffs(11, 74).mirror().addBox(0.0F, 0.0F, 0.0F, 9.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.5F, 10.0F, 1.8F, 0.2618F, 0.0F, 0.0F));
        PartDefinition f2 = pantsAnchor.addOrReplaceChild("f2", CubeListBuilder.create().texOffs(11, 74).mirror().addBox(0.0F, 0.0F, 0.0F, 9.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.5F, 12.0F, 1.8F, 0.2618F, 0.0F, 0.0F));
        PartDefinition f3 = pantsAnchor.addOrReplaceChild("f3", CubeListBuilder.create().texOffs(11, 74).mirror().addBox(0.0F, 0.0F, 0.0F, 9.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.5F, 14.0F, 1.8F, 0.2618F, 0.0F, 0.0F));
        PartDefinition d1 = pantsAnchor.addOrReplaceChild("d1", CubeListBuilder.create().texOffs(0, 74).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 10.0F, -2.0F, 0.0F, 0.0F, -0.2618F));
        PartDefinition d2 = pantsAnchor.addOrReplaceChild("d2", CubeListBuilder.create().texOffs(0, 74).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 12.0F, -2.0F, 0.0F, 0.0F, -0.2618F));
        PartDefinition d3 = pantsAnchor.addOrReplaceChild("d3", CubeListBuilder.create().texOffs(0, 74).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 14.0F, -2.0F, 0.0F, 0.0F, -0.2618F));
        PartDefinition e1 = pantsAnchor.addOrReplaceChild("e1", CubeListBuilder.create().texOffs(0, 74).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.2F, 9.8F, -2.0F, 0.0F, 0.0F, 0.2618F));
        PartDefinition e2 = pantsAnchor.addOrReplaceChild("e2", CubeListBuilder.create().texOffs(0, 74).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.2F, 11.8F, -2.0F, 0.0F, 0.0F, 0.2618F));
        PartDefinition e3 = pantsAnchor.addOrReplaceChild("e3", CubeListBuilder.create().texOffs(0, 74).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.2F, 13.8F, -2.0F, 0.0F, 0.0F, 0.2618F));
        PartDefinition a1 = pantsAnchor.addOrReplaceChild("a1", CubeListBuilder.create().texOffs(0, 67).mirror().addBox(0.0F, 0.0F, 0.0F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, 10.0F, -3.0F, -0.2793F, 0.0F, 0.0F));
        PartDefinition a2 = pantsAnchor.addOrReplaceChild("a2", CubeListBuilder.create().texOffs(0, 67).mirror().addBox(0.0F, 0.0F, 0.0F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, 12.0F, -3.0F, -0.2793F, 0.0F, 0.0F));
        PartDefinition a3 = pantsAnchor.addOrReplaceChild("a3", CubeListBuilder.create().texOffs(0, 67).mirror().addBox(0.0F, 0.0F, 0.0F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, 14.0F, -3.0F, -0.2793F, 0.0F, 0.0F));

        PartDefinition legL = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.0F));
        PartDefinition c1 = legL.addOrReplaceChild("c1", CubeListBuilder.create().texOffs(22, 67).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, -2.0F, -3.0F, -0.2618F, 0.0F, 0.0F));
        PartDefinition c2 = legL.addOrReplaceChild("c2", CubeListBuilder.create().texOffs(22, 67).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, 0.0F, -3.0F, -0.2618F, 0.0F, 0.0F));
        PartDefinition c3 = legL.addOrReplaceChild("c3", CubeListBuilder.create().texOffs(22, 67).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, 2.0F, -3.0F, -0.2618F, 0.0F, 0.0F));
        PartDefinition legR = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-2.0F, 12.0F, 0.0F));
        PartDefinition b1 = legR.addOrReplaceChild("b1", CubeListBuilder.create().texOffs(13, 67).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, -2.0F, -3.0F, -0.2618F, 0.0F, 0.0F));
        PartDefinition b2 = legR.addOrReplaceChild("b2", CubeListBuilder.create().texOffs(13, 67).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, 0.0F, -3.0F, -0.2618F, 0.0F, 0.0F));
        PartDefinition b3 = legR.addOrReplaceChild("b3", CubeListBuilder.create().texOffs(13, 67).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, 2.0F, -3.0F, -0.2618F, 0.0F, 0.0F));
        return meshdefinition;
    }

    public static MeshDefinition createOutsideMesh()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition root = meshdefinition.getRoot();

        root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        var head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition top = head.addOrReplaceChild("top", CubeListBuilder.create().texOffs(0, 33).mirror().addBox(0.0F, 0.0F, 0.0F, 9.0F, 3.0F, 9.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-4.5F, -8.5F, -4.5F));
        PartDefinition back = head.addOrReplaceChild("back", CubeListBuilder.create().texOffs(37, 33).mirror().addBox(0.0F, 0.0F, 0.0F, 9.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.5F, -8.0F, 3.0F, 0.2618F, 0.0F, 0.0F));
        PartDefinition front1 = head.addOrReplaceChild("front1", CubeListBuilder.create().texOffs(58, 33).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.7F, -6.0F, -1.9F, 0.0F, 0.9948F, 0.0F));
        PartDefinition front2 = head.addOrReplaceChild("front2", CubeListBuilder.create().texOffs(67, 33).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.7F, -6.0F, -4.5F, 0.0F, -0.9948F, 0.0F));
        PartDefinition right1 = head.addOrReplaceChild("right1", CubeListBuilder.create().texOffs(0, 50).mirror().addBox(0.0F, 0.0F, 0.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-5.0F, -13.0F, -4.9F, 0.0873F, 0.0873F, -0.2618F));
        PartDefinition right2 = head.addOrReplaceChild("right2", CubeListBuilder.create().texOffs(76, 33).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 6.0F, 9.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-4.0F, -8.0F, -4.5F, 0.0F, 0.0F, 0.3316F));
        PartDefinition right3 = head.addOrReplaceChild("right3", CubeListBuilder.create().texOffs(14, 50).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.0F, -7.2F, -4.3F, 0.0F, 0.0F, 0.3316F));
        PartDefinition left1 = head.addOrReplaceChild("left1", CubeListBuilder.create().texOffs(53, 50).mirror().addBox(0.0F, 0.0F, 0.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, -13.5F, -5.0F, 0.0873F, -0.0873F, 0.2618F));
        PartDefinition left2 = head.addOrReplaceChild("left2", CubeListBuilder.create().texOffs(23, 50).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, -8.0F, -4.5F, 0.0F, 0.0F, -0.3316F));
        PartDefinition left3 = head.addOrReplaceChild("left3", CubeListBuilder.create().texOffs(44, 50).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.7F, -6.5F, -4.3F, 0.0F, 0.0F, -0.3316F));
        PartDefinition medal = head.addOrReplaceChild("medal", CubeListBuilder.create().texOffs(7, 50).mirror().addBox(0.0F, 0.0F, 0.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-1.0F, -11.5F, -5.0F));
        PartDefinition front = head.addOrReplaceChild("front", CubeListBuilder.create().texOffs(97, 33).mirror().addBox(0.0F, 0.0F, 0.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-3.0F, -8.0F, -5.1F));

        var body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        var bodyAnchor = body.addOrReplaceChild("bodyAnchor", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
        bodyAnchor.addOrReplaceChild("fronter", CubeListBuilder.create().texOffs(0, 84).mirror().addBox(0.0F, 0.0F, 0.0F, 6.0F, 7.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-3.0F, 1.0F, -3.0F));
        bodyAnchor.addOrReplaceChild("backer", CubeListBuilder.create().texOffs(0, 102).mirror().addBox(0.0F, 0.0F, 0.0F, 6.0F, 10.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-3.0F, 1.0F, 1.7F));
        bodyAnchor.addOrReplaceChild("book", CubeListBuilder.create().texOffs(0, 93).mirror().addBox(0.0F, 0.0F, 0.0F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(1.0F, 1.2F, 2.7F, 0.0F, 0.0F, 0.7854F));

        var armLAnchor = root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(4.0F, 2.0F, 0.0F));
        armLAnchor.addOrReplaceChild("larm1", CubeListBuilder.create().texOffs(15, 84).mirror().addBox(0.0F, 0.0F, 0.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -3.0F, -2.5F, 0.0F, 0.0F, -0.1745F));
        armLAnchor.addOrReplaceChild("larm2", CubeListBuilder.create().texOffs(60, 84).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(1.7F, -2.0F, -2.0F, 0.0F, 0.0F, -0.3491F));
        armLAnchor.addOrReplaceChild("larm3", CubeListBuilder.create().texOffs(49, 84).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(1.7F, -1.0F, -2.0F, 0.0F, 0.0F, -0.1745F));
        armLAnchor.addOrReplaceChild("larm4", CubeListBuilder.create().texOffs(36, 84).mirror().addBox(0.0F, 0.0F, 0.0F, 2.0F, 5.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(0.8F, 3.2F, -2.0F));
        var armRAnchor = root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-4.0F, 2.0F, 0.0F));
        armRAnchor.addOrReplaceChild("rarm1", CubeListBuilder.create().texOffs(15, 96).mirror().addBox(0.0F, 0.0F, 0.0F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-3.0F, -4.0F, -2.5F, 0.0F, 0.0F, 0.1745F));
        armRAnchor.addOrReplaceChild("rarm2", CubeListBuilder.create().texOffs(36, 96).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-2.7F, -2.0F, -2.0F, 0.0F, 0.0F, 0.3491F));
        armRAnchor.addOrReplaceChild("rarm3", CubeListBuilder.create().texOffs(47, 96).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-2.7F, -1.0F, -2.0F, 0.0F, 0.0F, 0.1745F));
        armRAnchor.addOrReplaceChild("rarm4", CubeListBuilder.create().texOffs(58, 96).mirror().addBox(0.0F, 0.0F, 0.0F, 2.0F, 5.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(-2.8F, 3.2F, -2.0F));

        var bootL = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.39F, 8.5F, -2.49F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));
        bootL.addOrReplaceChild("boot2", CubeListBuilder.create().texOffs(0, 114).mirror().addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-2.0F, 8.0F, -2.5F));
        bootL.addOrReplaceChild("fb2", CubeListBuilder.create().texOffs(19, 114).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-2.0F, 9.0F, -3.0F));
        var bootR = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 8.5F, -2.51F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offset(-2.0F, 12.0F, 0.0F));
        bootR.addOrReplaceChild("boot1", CubeListBuilder.create().texOffs(0, 114).mirror().addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-2.0F, 8.0F, -2.5F));
        bootR.addOrReplaceChild("fb1", CubeListBuilder.create().texOffs(19, 114).mirror().addBox(0.0F, 0.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-1.0F, 9.0F, -3.0F));

        return meshdefinition;
    }


}