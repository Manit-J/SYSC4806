$(document).ready(function() {
    function loadAddressBooks() {
        $.get("/addressBooks", function(data) {
            $.each(data._embedded.addressBooks, function(i, book) {
                let href = book._links.self.href;
                let id = href.split("/").pop();
                $("#addressBooks").append(
                    `<div><h3>Address Book #${id}</h3>
                     <button onclick="viewBook(${id})">View</button></div>`
                );
            });
        });
    }

    window.viewBook = function(id) {
        $.get(`/addressBooks/${id}/myBuddies`, function(data) {
            let html = `<h3>Address Book #${id}</h3><ul>`;
            if (data._embedded.buddyInfoes) {
                $.each(data._embedded.buddyInfoes, function(i, b) {
                    html += `<li>${b.name} - ${b.phoneNumber} - ${b.address}</li>`;
                });
            }
            html += '</ul>';
            $("#addressBooks").html(html);
        });
    }

    window.createAddressBook = function() {
        $.ajax({
            url: "/addressBooks",
            type: "POST",
            contentType: "application/json", // important
            data: JSON.stringify({}),        // empty JSON object
            success: function() {
                alert("New address book created!");
            },
            error: function(xhr) {
                alert("Error creating address book: " + xhr.responseText);
            }
        });
    };

    window.addBuddy = function() {
        const bookId = $("#bookId").val();
        const name = $("#buddyName").val();
        const address = $("#buddyAddress").val();
        const phoneNumber = $("#buddyPhone").val();

        if (!bookId || !name || !address || !phoneNumber) {
            alert("Please fill in all fields.");
            return;
        }

        // Post directly to /buddyInfoes
        $.ajax({
            url: "/buddyInfoes",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                name: name,
                address: address,
                phoneNumber: phoneNumber,
                addressBook: `http://localhost:8080/addressBooks/${bookId}`
            }),
            success: function() {
                alert(`Buddy added to Address Book #${bookId}`);
                viewBook(bookId);
            },
            error: function(xhr) {
                alert("Error adding buddy: " + xhr.responseText);
            }
        });
    };

    loadAddressBooks();
});