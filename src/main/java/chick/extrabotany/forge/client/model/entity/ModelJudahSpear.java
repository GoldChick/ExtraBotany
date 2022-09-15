package chick.extrabotany.forge.client.model.entity;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;


public class ModelJudahSpear<T extends Entity> extends EntityModel<T>
{
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
     private final ModelPart main;
    private final ModelPart arrowa1;
    private final ModelPart arrowa2;
    private final ModelPart arrowa3;
    private final ModelPart arrowa4;
    private final ModelPart arrowb1;
    private final ModelPart arrowb2;
    private final ModelPart arrowb3;
    private final ModelPart arrowb4;

    public ModelJudahSpear(ModelPart root)
    {
        this.main = root.getChild("main");
        this.arrowa1 = root.getChild("arrowa1");
        this.arrowa2 = root.getChild("arrowa2");
        this.arrowa3 = root.getChild("arrowa3");
        this.arrowa4 = root.getChild("arrowa4");
        this.arrowb1 = root.getChild("arrowb1");
        this.arrowb2 = root.getChild("arrowb2");
        this.arrowb3 = root.getChild("arrowb3");
        this.arrowb4 = root.getChild("arrowb4");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(4, 0).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 24.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -4.0F, 0.0F));

        PartDefinition arrowa1 = partdefinition.addOrReplaceChild("arrowa1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 18.3F, -1.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition arrowa2 = partdefinition.addOrReplaceChild("arrowa2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 18.0F, 1.0F, -0.3491F, 0.0F, 0.0F));

        PartDefinition arrowa3 = partdefinition.addOrReplaceChild("arrowa3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 18.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

        PartDefinition arrowa4 = partdefinition.addOrReplaceChild("arrowa4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 18.4F, 0.0F, 0.0F, 0.0F, -0.3491F));

        PartDefinition arrowb1 = partdefinition.addOrReplaceChild("arrowb1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 18.3F, -0.3F, 0.4363F, 0.7854F, 0.0F));

        PartDefinition arrowb2 = partdefinition.addOrReplaceChild("arrowb2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.3F, 18.3F, -1.0F, 0.4363F, -0.7854F, 0.0F));

        PartDefinition arrowb3 = partdefinition.addOrReplaceChild("arrowb3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 18.3F, 1.3F, 0.0F, 0.7854F, -0.4363F));

        PartDefinition arrowb4 = partdefinition.addOrReplaceChild("arrowb4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.3F, 17.9F, 0.7F, 0.0F, -0.7854F, 0.4363F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
    {
        main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        arrowa1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        arrowa2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        arrowa3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        arrowa4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        arrowb1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        arrowb2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        arrowb3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        arrowb4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}