package cmv;

@FunctionalInterface
public interface Validate
{
	boolean validate(ChessBoard board, Square from, Square to);
}
