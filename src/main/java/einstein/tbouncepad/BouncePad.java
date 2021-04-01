package einstein.tbouncepad;

import einstein.tbouncepad.TinkersBouncePad.ModInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BouncePad extends Block {
	public static final IntegerProperty DIRECTION = IntegerProperty.create("direction", 0, 7);
	protected static final VoxelShape BOTTOM = Block.makeCuboidShape(0, 0, 0, 16, 8, 16);
    protected static final VoxelShape TOP = Block.makeCuboidShape(2, 8, 2, 14, 10, 14);
	protected static final VoxelShape[] POINTS = new VoxelShape[] {
		VoxelShapes.or(BOTTOM, TOP, Block.makeCuboidShape(10, 8, 1, 6, 11, 5)), // North
		VoxelShapes.or(BOTTOM, TOP, Block.makeCuboidShape(11, 8, 1, 15, 11, 5)), // North-east
		VoxelShapes.or(BOTTOM, TOP, Block.makeCuboidShape(11, 8, 6, 15, 11, 10)), // East
		VoxelShapes.or(BOTTOM, TOP, Block.makeCuboidShape(11, 8, 11, 15, 11, 15)), // South-east
		VoxelShapes.or(BOTTOM, TOP, Block.makeCuboidShape(6, 8, 11, 10, 11, 15)), // South
		VoxelShapes.or(BOTTOM, TOP, Block.makeCuboidShape(1, 8, 11, 5, 11, 15)), // South-west
		VoxelShapes.or(BOTTOM, TOP, Block.makeCuboidShape(1, 8, 6, 5, 11, 10)), // West
		VoxelShapes.or(BOTTOM, TOP, Block.makeCuboidShape(1, 8, 1, 5, 11, 5)), // North-west
	};

	public BouncePad(final Block.Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(DIRECTION, Integer.valueOf(0)));
	}

	public VoxelShape getShape(final BlockState state, final IBlockReader worldIn, final BlockPos pos, final ISelectionContext context) {
		return POINTS[state.get(DIRECTION)];
	}

	public VoxelShape getCollisionShape(final BlockState state, final IBlockReader worldIn, final BlockPos pos, final ISelectionContext context) {
		return VoxelShapes.empty();
	}

	public boolean isNormalCube(final BlockState state, final IBlockReader worldIn, final BlockPos pos) {
		return false;
	}

	public BlockState getStateForPlacement(final BlockItemUseContext context) {
		return this.getDefaultState().with(DIRECTION, Integer.valueOf(MathHelper.floor(((180.0F + context.getPlacementYaw()) * 8.0F / 360.0F) + 0.5D) & 7));
	}

	public void fillStateContainer(final StateContainer.Builder<Block, BlockState> builder) {
		builder.add(DIRECTION);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(DIRECTION, Integer.valueOf(rot.rotate(state.get(DIRECTION), 8)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.with(DIRECTION, Integer.valueOf(mirrorIn.mirrorRotation(state.get(DIRECTION), 8)));
	}

	public void onEntityCollision(final BlockState state, final World worldIn, final BlockPos pos, final Entity entityIn) {
		double moveX = 0.0F;
		double moveZ = 0.0F;
		double speed = 0.25F;
		if (!entityIn.isCrouching()) {
			switch (state.get(DIRECTION)) {
				default: {	// North
					moveZ -= speed;
					break;
				}
				case 1: {	// North-east
					moveZ -= speed;
					moveX += speed;
					break;
				}
				case 2: {	// East
					moveX += speed;
					break;
				}
				case 3: {	// South-east
					moveZ += speed;
					moveX += speed;
					break;
				}
				case 4: {	// South
					moveZ += speed;
					break;
				}
				case 5: {	// South-west
					moveZ += speed;
					moveX -= speed;
					break;
				}
				case 6: {	// West
					moveX -= speed;
					break;
				}
				case 7: {	// North-west
					moveZ -= speed;
					moveX -= speed;
					break;
				}
			}
			entityIn.addVelocity(moveX, 0.5F, moveZ);
		}
		if (entityIn instanceof ItemEntity) {
			++entityIn.lastTickPosY;
			entityIn.addVelocity(moveX, 0.5F, moveZ);
		}
		entityIn.fallDistance = 0.0F;
		if (ModInit.BOUNCE_SOUND.get()) {
			worldIn.playSound(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, soundType.getStepSound(), SoundCategory.BLOCKS, soundType.getVolume() / 2.0F, soundType.getPitch() * 0.65F, false);	
		}
	}
}
