const BASE_URL = 'http://localhost:8080'

export const loginUser = async (userData) => {
    return await fetch(`${BASE_URL}/login`,
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData),
        })
}

export const registerUser = async (userData) =>{
    return await fetch(`${BASE_URL}/addStudent`,
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData),
        })

}

export const addStudentCourse = async (userData) =>{
    return await fetch(`${BASE_URL}/materie/addStudent`,
        {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData),
        })

}