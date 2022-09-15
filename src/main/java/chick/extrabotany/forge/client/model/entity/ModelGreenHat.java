package chick.extrabotany.forge.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ModelGreenHat<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "modelgreenhat"), "main");
	private final ModelPart Shape1;
	private final ModelPart Shape2;

	public ModelGreenHat(ModelPart root) {
		this.Shape1 = root.getChild("Shape1");
		this.Shape2 = root.getChild("Shape2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Shape1 = partdefinition.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(0, 17).mirror().addBox(0.0F, 0.0F, 0.0F, 12.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.0F, -9.0F, -6.0F));

		PartDefinition Shape2 = partdefinition.addOrReplaceChild("Shape2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 0.0F, 0.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, -18.0F, -4.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Shape1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Shape2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}