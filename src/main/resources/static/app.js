
fetch("/api/classes")
    .then(response => {
        if (!response.ok) {
            throw new Error("Backend connection failed");
        }
        return response.json();
    })
    .then(classes => {
        const classList = document.getElementById("classList");
        const classSelect = document.getElementById("classSelect");

        classList.innerHTML = "";
        classSelect.innerHTML = '<option value="">Select a class</option>';

        classes.forEach(item => {
            const card = document.createElement("div");
            card.className = "class-card";

            const title = document.createElement("h3");
            title.textContent = item.class_name || item.className || item.name || item.title || "Fitness Class";

            const trainer = document.createElement("p");
            trainer.textContent = "Trainer: " +
                (item.trainer_name || item.trainerName || item.trainer || "Trainer not assigned");

            const price = document.createElement("p");
            price.textContent = "Price: ₹" + (item.price ?? 50);

            card.append(title, trainer, price);
            classList.appendChild(card);

            const option = document.createElement("option");
            option.value = item.id;
            option.textContent = title.textContent + " - " +
                (item.trainer_name || item.trainerName || item.trainer || "Trainer not assigned");

            classSelect.appendChild(option);
        });
    })
    .catch(error => {
        console.error("Error loading classes:", error);
        document.getElementById("classList").textContent =
            "Unable to load classes. Please check the backend.";
    });

	const bookingForm = document.getElementById("bookingForm");

	if (bookingForm) {
	    bookingForm.addEventListener("submit", async function (event) {
	        event.preventDefault();

	        const memberName =
	            bookingForm.elements["memberName"].value.trim();
	        const memberEmail =
	            bookingForm.elements["memberEmail"].value.trim();
	        const classId =
	            bookingForm.elements["classId"].value;
	        const message = document.getElementById("bookingMessage");

	        if (!memberName || !memberEmail || !classId) {
	            message.textContent =
	                "Please fill in all fields and select a class.";
	            return;
	        }

	        message.textContent = "Saving your booking...";

	        try {
	            const response = await fetch("/api/bookings", {
	                method: "POST",
	                headers: {
	                    "Content-Type": "application/json"
	                },
	                body: JSON.stringify({
	                    memberName: memberName,
	                    memberEmail: memberEmail,
	                    classId: Number(classId)
	                })
	            });

	            if (!response.ok) {
	                throw new Error(await response.text());
	            }

	            const booking = await response.json();

	            message.textContent =
	                "Booking successful! Booking ID: " + booking.id;

	            bookingForm.reset();

	        } 
			catch (error) {
			    console.error("Booking error:", error);
			    message.textContent = "Booking failed: " + error.message;
			
	        }
	    });
	}