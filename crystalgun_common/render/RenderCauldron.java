//Copyright (C) 2013  torresmon235
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.
package torresmon235.crystalgun.render;

import org.lwjgl.opengl.GL11;

import torresmon235.crystalgun.blocks.BlockCGCauldron;
import torresmon235.crystalgun.common.CrystalGunMain;
import torresmon235.crystalgun.library.RenderID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.ForgeHooksClient;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderCauldron implements ISimpleBlockRenderingHandler
{
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderblocks) 
	{

	}

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
    	ForgeHooksClient.bindTexture("/torresmon235/crystalgun/textures/blocks.png", 3);
    	renderer.renderStandardBlock(block, x, y, z);
        Tessellator var5 = Tessellator.instance;
        var5.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
        float var6 = 1.0F;
        int var7 = block.colorMultiplier(renderer.blockAccess, x, y, z);
        float var8 = (float)(var7 >> 16 & 255) / 255.0F;
        float var9 = (float)(var7 >> 8 & 255) / 255.0F;
        float var10 = (float)(var7 & 255) / 255.0F;
        float var12;

        if (EntityRenderer.anaglyphEnable)
        {
            float var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
            var12 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
            float var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
            var8 = var11;
            var9 = var12;
            var10 = var13;
        }

        var5.setColorOpaque_F(var6 * var8, var6 * var9, var6 * var10);
        short var16 = 18;
        var12 = 0.125F;
        renderer.renderSouthFace(block, (double)((float)x - 1.0F + var12), (double)y, (double)z, var16);
        renderer.renderNorthFace(block, (double)((float)x + 1.0F - var12), (double)y, (double)z, var16);
        renderer.renderWestFace(block, (double)x, (double)y, (double)((float)z - 1.0F + var12), var16);
        renderer.renderEastFace(block, (double)x, (double)y, (double)((float)z + 1.0F - var12), var16);
        short var17 = 17;
        renderer.renderTopFace(block, (double)x, (double)((float)y - 1.0F + 0.25F), (double)z, var17);
        renderer.renderBottomFace(block, (double)x, (double)((float)y + 1.0F - 0.75F), (double)z, var17);
        int var14 = renderer.blockAccess.getBlockMetadata(x, y, z);
        ForgeHooksClient.unbindTexture();

        if (var14 > 0)
        {
        	ForgeHooksClient.bindTexture("/terrain.png", 2);
            short var15 = 237;

            if (var14 > 3)
            {
                var14 = 3;
            }

            renderer.renderTopFace(block, (double)x, (double)((float)y - 1.0F + (6.0F + (float)var14 * 3.0F) / 16.0F), (double)z, var15);
            ForgeHooksClient.unbindTexture();
        }

        return true;
    }

    public boolean shouldRender3DInInventory()
    {
         // This is where it asks if you want the renderInventory part called or not.
         return false; // Change to 'true' if you want the Inventory render to be called.
    }

    public int getRenderId()
    {
         // This is one place we need that renderId from earlier.
         return RenderID.Cauldron;
    }
}