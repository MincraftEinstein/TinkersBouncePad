package einstein.tbouncepad;

import einstein.tbouncepad.TinkersBouncePad.ModInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BouncePad extends Block implements SimpleWaterloggedBlock {
	public static final IntegerProperty DIRECTION = IntegerProperty.create("direction", 0, 7);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	protected static final VoxelShape BOTTOM = Block.box(0, 0, 0, 16, 8, 16);
    protected static final VoxelShape TOP = Block.box(2, 8, 2, 14, 10, 14);
	protected static final VoxelShape[] SHAPES = new VoxelShape[] {
		Shapes.or(BOTTOM, TOP, Block.box(6, 8, 1, 10, 11, 5)), // North
		Shapes.or(BOTTOM, TOP, Block.box(11, 8, 1, 15, 11, 5)), // North-east
		Shapes.or(BOTTOM, TOP, Block.box(11, 8, 6, 15, 11, 10)), // East
		Shapes.or(BOTTOM, TOP, Block.box(11, 8, 11, 15, 11, 15)), // South-east
		Shapes.or(BOTTOM, TOP, Block.box(6, 8, 11, 10, 11, 15)), // South
		Shapes.or(BOTTOM, TOP, Block.box(1, 8, 11, 5, 11, 15)), // South-west
		Shapes.or(BOTTOM, TOP, Block.box(1, 8, 6, 5, 11, 10)), // West
		Shapes.or(BOTTOM, TOP, Block.box(1, 8, 1, 5, 11, 5)), // North-west
	};
	
	public BouncePad(final Block.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(DIRECTION, Integer.valueOf(0)).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return SHAPES[state.getValue(DIRECTION)];
	}
	
	public VoxelShape getCollisionShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}
	
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(DIRECTION, Integer.valueOf(Mth.floor(((180 + context.getRotation()) * 8 / 360) + 0.5F) & 7));
	}
	
	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState p_56131_) {
		return p_56131_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_56131_);
	}
	
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(DIRECTION, WATERLOGGED);
	}
	
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(DIRECTION, Integer.valueOf(rotation.rotate(state.getValue(DIRECTION), 8)));
	}
	
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.setValue(DIRECTION, Integer.valueOf(mirror.mirror(state.getValue(DIRECTION), 8)));
	}
	
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		double moveX = 0;
		double moveZ = 0;
		double speed = 0.25F;
		if (!entity.isCrouching()) {
			switch (state.getValue(DIRECTION)) {
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
			entity.push(moveX, 0.5F, moveZ);
			if (ModInit.BOUNCE_SOUND.get()) {
				level.playLocalSound(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, soundType.getStepSound(), SoundSource.BLOCKS, soundType.getVolume() / 2.0F, soundType.getPitch() * 0.65F, false);	
			}
		}
		if (entity instanceof ItemEntity) {
			++entity.yOld;
			entity.push(moveX, 0.5F, moveZ);
		}
		entity.fallDistance = 0.0F;
	}
}
