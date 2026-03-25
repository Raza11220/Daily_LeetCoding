class Solution(object):
    def canPartitionGrid(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: bool
        """
        rows = len(grid)
        cols = len(grid[0])
        
        # Total sum
        total = sum(sum(row) for row in grid)
        
        # If total is odd, cannot split equally
        if total % 2 != 0:
            return False
        
        half = total // 2
        
        # 🔹 Check horizontal partition
        curr_sum = 0
        for r in range(rows):
            curr_sum += sum(grid[r])
            if curr_sum == half:
                return True
        
        # 🔹 Check vertical partition
        curr_sum = 0
        for c in range(cols):
            col_sum = 0
            for r in range(rows):
                col_sum += grid[r][c]
            
            curr_sum += col_sum
            if curr_sum == half:
                return True
        
        return False