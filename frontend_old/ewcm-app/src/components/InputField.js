const InputField = ({ placeholder, id, onChange, value, onKeyUp }) => {
    return (
        <div className="input-group flex-grow-1 me-3">
            <span className="input-group-text">Participant's ID:</span>
            <input 
                id={id}
                type="text" 
                className="form-control" 
                placeholder={placeholder} 
                onChange={onChange} 
                value={value}
                onKeyUp={onKeyUp}
            />
            </div>
    );
}

export default InputField;