package einstein.tbouncepad;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BouncePad extends HorizontalBlock
{
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    protected static final VoxelShape BASE = Block.makeCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
    protected static final VoxelShape MIDDLE = Block.makeCuboidShape(2.0, 8.0, 2.0, 14.0, 10.0, 14.0);
    protected static final VoxelShape POINT_NORTH = Block.makeCuboidShape(10.0, 8.0, 1.0, 6.0, 11.0, 5.0);
    protected static final VoxelShape POINT_SOUTH = Block.makeCuboidShape(6.0, 8.0, 11.0, 10.0, 11.0, 15.0);
    protected static final VoxelShape POINT_WEST = Block.makeCuboidShape(1.0, 8.0, 6.0, 5.0, 11.0, 10.0);
    protected static final VoxelShape POINT_EAST = Block.makeCuboidShape(11.0, 8.0, 6.0, 15.0, 11.0, 10.0);
    protected static final VoxelShape NULL_SHAPE = Block.makeCuboidShape(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(BouncePad.BASE, new VoxelShape[] { BouncePad.MIDDLE, BouncePad.POINT_NORTH });
    protected static final VoxelShape SOUTH_SHAPE = VoxelShapes.or(BouncePad.BASE, new VoxelShape[] { BouncePad.MIDDLE, BouncePad.POINT_SOUTH });
    protected static final VoxelShape WEST_SHAPE = VoxelShapes.or(BouncePad.BASE, new VoxelShape[] { BouncePad.MIDDLE, BouncePad.POINT_WEST });
    protected static final VoxelShape EAST_SHAPE = VoxelShapes.or(BouncePad.BASE, new VoxelShape[] { BouncePad.MIDDLE, BouncePad.POINT_EAST });
    
    public BouncePad(final Block.Properties properties) {
        super(properties);
        this.setDefaultState((this.stateContainer.getBaseState()).with(BouncePad.FACING, Direction.NORTH));
    }
    
    public VoxelShape getShape(final BlockState state, final IBlockReader worldIn, final BlockPos pos, final ISelectionContext context) {
        final Direction direction = state.get(BouncePad.FACING);
        if (direction == Direction.NORTH) {
            return BouncePad.NORTH_SHAPE;
        }
        if (direction == Direction.SOUTH) {
            return BouncePad.SOUTH_SHAPE;
        }
        if (direction == Direction.WEST) {
            return BouncePad.WEST_SHAPE;
        }
        return BouncePad.EAST_SHAPE;
    }
    
    public VoxelShape getCollisionShape(final BlockState state, final IBlockReader worldIn, final BlockPos pos, final ISelectionContext context) {
        return BouncePad.NULL_SHAPE;
    }
    
    public boolean isNormalCube(final BlockState state, final IBlockReader worldIn, final BlockPos pos) {
        return false;
    }
    
    public BlockState getStateForPlacement(final BlockItemUseContext context) {
        return this.getDefaultState().with(BouncePad.FACING, context.getPlacementHorizontalFacing());
    }
    
    public void fillStateContainer(final StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BouncePad.FACING);
    }
    
    public void onEntityCollision(final BlockState state, final World worldIn, final BlockPos pos, final Entity entityIn) {
        double moveX = 0.0F;
        double moveZ = 0.0F;
        if (!entityIn.isCrouching()) {
            switch (state.get(BouncePad.FACING)) {
                default: {
                    moveZ -= 0.25F;
                    break;
                }
                case SOUTH: {
                    moveZ += 0.25F;
                    break;
                }
                case EAST: {
                    moveX += 0.25F;
                    break;
                }
                case WEST: {
                    moveX -= 0.25F;
                    break;
                }
            }
            entityIn.addVelocity(moveX, 0.5, moveZ);
        }
        if (entityIn instanceof ItemEntity) {
            ++entityIn.lastTickPosY;
            entityIn.addVelocity(moveX, 0.5, moveZ);
        }
        entityIn.fallDistance = 0.0F;
        worldIn.playSound(0.0, 0.0, 0.0, SoundEvents.BLOCK_SLIME_BLOCK_STEP, SoundCategory.BLOCKS, 5.0F, 5.0F, false);
    }
}
