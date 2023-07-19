<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Update Railway Crossing</title>
    
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }
        
        h2 {
            text-align: center;
            margin-top: 20px;
            color: #4CAF50;
        }

        /* Reset styles for form elements */
        form, input, label, button {
            margin: 0;
            padding: 0;
            border: none;
            background: none;
        }

        .update-form {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #ffffff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        .form-input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box; /* Fix input width issue */
        }

        .form-button {
            display: block;
            width: 100%;
            padding: 12px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .form-button:hover {
            background-color: #45a049;
        }

        .form-button:active {
            background-color: #367c39;
        }
    </style>
</head>
<body>
    <h2>Update Railway Crossing</h2>
    
    
    <div class="update-form">
        <form action="UpdateCrossingsServlet" method="POST">
        	
			<div class="form-group">
			<label class="form-label" for="name">ID:</label>
    		<input type="text" id="id" name="crossingId" value="${crossing.id}" class="form-input" required>
			</div>
			
            <div class="form-group">
                <label class="form-label" for="name">Name:</label>
                <input type="text" id="name" name="name" value="${crossing.name}" class="form-input" required>
            </div>

            <!-- Add other fields for updating the crossing data -->
            <div class="form-group">
                <label class="form-label" for="address">Address:</label>
                <input type="text" id="address" name="address" value="${crossing.address}" class="form-input" required>
            </div>
            <div class="form-group">
                <label class="form-label" for="landmark">Landmark:</label>
                <input type="text" id="landmark" name="landmark" value="${crossing.landmark}" class="form-input" required>
            </div>
            <div class="form-group">
                <label class="form-label" for="trainSchedules">Train Schedule:</label>
                <input type="text" id="trainSchedules" name="trainSchedules" value="${crossing.trainSchedules}" class="form-input" required>
            </div>
            <div class="form-group">
                <label class="form-label" for="personInCharge">Person In-Charge:</label>
                <input type="text" id="personInCharge" name="personInCharge" value="${crossing.personInCharge}" class="form-input" required>
            </div>
            <div class="form-group">
                <label class="form-label" for="status">Status:</label>
                <select id="status" name="status" class="form-input" required>
                    <option value="open" ${crossing.status == 'open' ? 'selected' : ''}>Open</option>
                    <option value="closed" ${crossing.status == 'closed' ? 'selected' : ''}>Closed</option>
                </select>
            </div>

            <button type="submit" class="form-button">Submit</button>
        </form>
    </div>
</body>
</html>
