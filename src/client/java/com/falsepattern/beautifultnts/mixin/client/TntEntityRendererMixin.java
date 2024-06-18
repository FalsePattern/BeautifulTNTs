/*
 * Beautiful TNTs
 *
 * Copyright (C) 2024 FalsePattern
 * All Rights Reserved
 *
 * The above copyright notice and this permission notice
 * shall be included in all copies or substantial portions of the Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, only version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 * This program comes with additional permissions according to Section 7 of the
 * GNU General Public License. See the full LICENSE file for details.
 */

package com.falsepattern.beautifultnts.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.TntEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.TntEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TntEntityRenderer.class)
public abstract class TntEntityRendererMixin {
    @Redirect(method = "render(Lnet/minecraft/entity/TntEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/TntMinecartEntityRenderer;renderFlashingBlock(Lnet/minecraft/client/render/block/BlockRenderManager;Lnet/minecraft/block/BlockState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IZ)V"), require = 1)
    private void renderFlashingBlockFancy(BlockRenderManager blockRenderManager, BlockState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, boolean drawFlash, @Local(ordinal = 0, argsOnly = true) TntEntity entity) {
                float fuse = MathHelper.clamp(entity.getFuse() / 80f, 0, 1);
        int i;
        if (drawFlash) {
            i = OverlayTexture.packUv(OverlayTexture.getU(1.0F - fuse), 10);
        } else {
            i = OverlayTexture.DEFAULT_UV;
        }

        blockRenderManager.renderBlockAsEntity(state, matrices, vertexConsumers, light, i);
    }
}
