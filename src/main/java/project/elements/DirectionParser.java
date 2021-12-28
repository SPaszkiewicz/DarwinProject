package project.elements;

import project.orientation.MoveDirection;

public class DirectionParser
{
    public static MoveDirection[] toDirection(int[] numbers)
    {
        MoveDirection[] gens = new MoveDirection[32];
        for (int i=0; i < 32; i += 1)
        {
            switch (numbers[i])
            {
                case 0: gens[i] = MoveDirection.FORWARD; break;
                case 1: gens[i] = MoveDirection.LIT_RIGHT; break;
                case 2: gens[i] = MoveDirection.RIGHT; break;
                case 3: gens[i] = MoveDirection.MOR_RIGHT; break;
                case 4: gens[i] = MoveDirection.BACKWARD; break;
                case 5: gens[i] = MoveDirection.MOR_LEFT; break;
                case 6: gens[i] = MoveDirection.LEFT; break;
                case 7: gens[i] = MoveDirection.LIT_LEFT; break;
            }
        }
        return gens;
    }
}
